package openwis.pilot.awisc.server.manager.jobs;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.provider.json.JSONProvider;
import org.apache.karaf.scheduler.Job;
import org.apache.karaf.scheduler.JobContext;

import openwis.pilot.awisc.server.common.dto.LdshDTO;
import openwis.pilot.awisc.server.manager.cxf.LdshAwiscIndexingService;
import openwis.pilot.awisc.server.manager.service.AwiscIndexingService;
import openwis.pilot.awisc.server.manager.service.LdshService;
import openwis.pilot.common.dto.awisc.LdshIndexDTO;

public class LdshIndexerJob implements Job {

	private static final Logger logger = Logger.getLogger(LdshIndexerJob.class.getName());

	@Override
	public void execute(JobContext context) {
		Map<String, Serializable> jobConfig = context.getConfiguration();
		AwiscIndexingService awiscIndexingService = (AwiscIndexingService) jobConfig.get("awiscIndexingService");
		LdshService ldshService = (LdshService) jobConfig.get("ldshService");
		logger.info("Executing " + this.getClass().getSimpleName() + " ...");
		List<LdshDTO> ldshs = ldshService.getLdsh();
		for (LdshDTO ldsh : ldshs) {
			if (ldsh.getRegistrationDate() == null) {
				continue;
			}
			logger.info("Processing LDSH: " + ldsh.getSystemId());

			List<JSONProvider<?>> providers = new ArrayList<JSONProvider<?>>();
			JSONProvider<?> jsonProvider = new JSONProvider<>();
			jsonProvider.setDropRootElement(true);
			jsonProvider.setDropCollectionWrapperElement(true);
			jsonProvider.setSerializeAsArray(true);
			jsonProvider.setSupportUnwrapped(true);
			providers.add(jsonProvider);

			LdshAwiscIndexingService ldshAwiscIndexingService = JAXRSClientFactory.create(ldsh.getIndexServiceBaseUrl(),
					LdshAwiscIndexingService.class, providers, true);

			LdshIndexDTO dto = ldshAwiscIndexingService.index();
			try {
				awiscIndexingService.index(dto);
			} catch (UnsupportedEncodingException e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
			}
		}

	}

}
