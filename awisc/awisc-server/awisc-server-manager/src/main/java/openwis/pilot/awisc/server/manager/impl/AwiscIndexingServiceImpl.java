package openwis.pilot.awisc.server.manager.impl;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.apache.karaf.scheduler.Scheduler;
import org.ops4j.pax.cdi.api.OsgiService;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.quartz.SchedulerException;

import com.eurodyn.qlack2.fuse.search.api.AdminService;
import com.eurodyn.qlack2.fuse.search.api.IndexingService;
import com.eurodyn.qlack2.fuse.search.api.SearchService;
import com.eurodyn.qlack2.fuse.search.api.dto.IndexingDTO;
import com.eurodyn.qlack2.fuse.search.api.request.CreateIndexRequest;

import openwis.pilot.awisc.server.common.dto.WmoCodeDTO;
import openwis.pilot.awisc.server.manager.jobs.LdshIndexerJob;
import openwis.pilot.awisc.server.manager.service.AwiscIndexingService;
import openwis.pilot.awisc.server.manager.service.LdshService;
import openwis.pilot.awisc.server.manager.service.WmoCodeService;
import openwis.pilot.awisc.server.manager.util.Util;
import openwis.pilot.common.dto.awisc.LdshIndexDTO;

@Singleton
@Transactional
@OsgiServiceProvider(classes = { AwiscIndexingService.class })
public class AwiscIndexingServiceImpl implements AwiscIndexingService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -566681990121285912L;

	private static final Logger logger = Logger.getLogger(AwiscIndexingServiceImpl.class.getName());

	@Inject
	@OsgiService
	AdminService adminService;

	@Inject
	@OsgiService
	IndexingService indexingService;

	@Inject
	@OsgiService
	SearchService searchService;

	@Inject
	WmoCodeService wmoCodeService;
	
	@Inject
	LdshService ldshService;

	static String LDSH_INDEX_NAME = "ldsh";
	static String LDSH_INDEX_TYPE = "ldsh-dto";
	static String LDSH_INDEX_JSON_PATH = "/ldsh-index.json";

	static String WMO_CODE_INDEX_NAME = "wmo-code";
	static String WMO_CODE_INDEX_TYPE = "wmo-code-dto";
	static String WMO_CODE_INDEX_JSON_PATH = "/wmo-code-index.json";
	

	/**
	 * Creates the ES indexes
	 * 
	 * @throws IOException
	 */
	private void createIndexes() throws IOException {
		if (!adminService.indexExists("/" + WMO_CODE_INDEX_NAME + "/_mapping/" + WMO_CODE_INDEX_TYPE)) {
			if (!adminService.indexExists(WMO_CODE_INDEX_NAME)) {
				String targetFileStr = Util.readResource(WMO_CODE_INDEX_JSON_PATH, StandardCharsets.UTF_8.name());

				CreateIndexRequest createIndexRequest = new CreateIndexRequest();
				createIndexRequest.setName("/" + WMO_CODE_INDEX_NAME);
				createIndexRequest.setIndexMapping(targetFileStr);
				adminService.createIndex(createIndexRequest);
				logger.info("Index " + WMO_CODE_INDEX_NAME + " created");
			}
		} else {
			logger.info("Index for " + WMO_CODE_INDEX_NAME + " already exist");
		}

		if (!adminService.indexExists("/" + LDSH_INDEX_NAME + "/_mapping/" + LDSH_INDEX_TYPE)) {
			if (!adminService.indexExists(LDSH_INDEX_NAME)) {
				String targetFileStr = Util.readResource(LDSH_INDEX_JSON_PATH, StandardCharsets.UTF_8.name());

				CreateIndexRequest createIndexRequest = new CreateIndexRequest();
				createIndexRequest.setName(LDSH_INDEX_NAME);
				createIndexRequest.setIndexMapping(targetFileStr);
				adminService.createIndex(createIndexRequest);
				logger.info("Index " + LDSH_INDEX_NAME + " created");
			}
		} else {
			logger.info("Index for " + LDSH_INDEX_NAME + " already exist");
		}
	}

	/**
	 * Reads WMO codes from the DB and indexes them in ES
	 * @throws UnsupportedEncodingException 
	 */
	private void indexWmoCodes() throws UnsupportedEncodingException {
		List<WmoCodeDTO> wmoCodes = wmoCodeService.getWmoCodes();
		for (WmoCodeDTO wmoCode : wmoCodes) {
			String safeCode = encodeElasticsearchId(wmoCode.getCode());
			if (!searchService.exists(WMO_CODE_INDEX_NAME, WMO_CODE_INDEX_TYPE, safeCode)) {
				IndexingDTO idto = new IndexingDTO();
				idto.setId(String.valueOf(safeCode));
				idto.setIndex(WMO_CODE_INDEX_NAME);
				idto.setRefresh(false);
				idto.setSourceObject(wmoCode);
				idto.setType(WMO_CODE_INDEX_TYPE);
				indexingService.indexDocument(idto);
			}

		}
	}
	
	/**
	 * Will encode the input to something safe for ES REST URLs
	 * @param input
	 * @return encoded input
	 * @throws UnsupportedEncodingException
	 */
	private String encodeElasticsearchId(String input) throws UnsupportedEncodingException {
		return URLEncoder.encode(input, "UTF-8");
	}
	
	/**
	 * Schedules the job that will fetch LDSH information for indexing
	 * @throws IllegalArgumentException
	 * @throws SchedulerException
	 */
	private void scheduleLdshJob() throws Exception {
		Scheduler scheduler = Util.getService(Scheduler.class);
		Map<String,Serializable> jobConfig = new HashMap<String,Serializable>();
		jobConfig.put("awiscIndexingService", this);
		jobConfig.put("ldshService", ldshService);
		scheduler.schedule(new LdshIndexerJob(), scheduler.EXPR("0 * * * * ?").config(jobConfig));
	}


	/**
	 * Deploy-time tasks
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * @throws SchedulerException
	 */
	@PostConstruct
	public void prepare() throws Exception {
		createIndexes();
		indexWmoCodes();
		scheduleLdshJob();
		
	}

	@Override
	public void index(LdshIndexDTO dto) throws UnsupportedEncodingException {
		logger.info("Indexing document with id " + dto.getSystemId());
		String encoded = encodeElasticsearchId(dto.getSystemId());
		if (!searchService.exists(LDSH_INDEX_NAME, LDSH_INDEX_TYPE, encoded)) {
			IndexingDTO esDto = new IndexingDTO(LDSH_INDEX_NAME, LDSH_INDEX_TYPE, encoded, dto, false);
			indexingService.indexDocument(esDto);
		}

	}

}
