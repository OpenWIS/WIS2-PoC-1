package openwis.pilot.awisc.server.manager.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.ops4j.pax.cdi.api.OsgiService;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;

import com.eurodyn.qlack2.fuse.search.api.AdminService;
import com.eurodyn.qlack2.fuse.search.api.IndexingService;
import com.eurodyn.qlack2.fuse.search.api.dto.IndexingDTO;
import com.eurodyn.qlack2.fuse.search.api.request.CreateIndexRequest;

import openwis.pilot.awisc.server.manager.service.AwiscIndexingService;
import openwis.pilot.awisc.server.manager.util.Util;
import openwis.pilot.common.dto.awisc.LdshIndexDTO;

@Singleton
@Transactional
@OsgiServiceProvider(classes = { AwiscIndexingService.class })
public class AwiscIndexingServiceImpl implements AwiscIndexingService {
	
	private static final Logger logger = Logger.getLogger(AwiscIndexingServiceImpl.class.getName());

	@Inject
	@OsgiService
	AdminService adminService;
	
	@Inject
	@OsgiService
	IndexingService indexingService;
	
	static String INDEX_NAME = "ldsh";
	static String INDEX_TYPE = "_doc";

	/*@PostConstruct
	public void prepare() throws URISyntaxException, IOException {
		if (!adminService.indexExists("/" + INDEX_NAME + "/_mapping/" + INDEX_TYPE)) {
			if (!adminService.indexExists(INDEX_NAME)) {
				String targetFileStr = Util.readResource("/ldsh-index.json");

				CreateIndexRequest createIndexRequest = new CreateIndexRequest();
				createIndexRequest.setName(INDEX_NAME);
				createIndexRequest.setIndexMapping(targetFileStr);
				adminService.createIndex(createIndexRequest);
				logger.info("Index " + INDEX_NAME + " created");
			}
		} else {
			logger.info("Index for " + INDEX_NAME + " already exist");
		}
	}*/

	@Override
	public void index(LdshIndexDTO dto) {
		logger.info("Indexing document with id " + dto.getSystemId());
		IndexingDTO esDto = new IndexingDTO(INDEX_NAME, INDEX_TYPE, dto.getSystemId(), dto, false);
		indexingService.indexDocument(esDto);

	}

}
