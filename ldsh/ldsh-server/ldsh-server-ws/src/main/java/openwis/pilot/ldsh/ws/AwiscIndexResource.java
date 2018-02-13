package openwis.pilot.ldsh.ws;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.ops4j.pax.cdi.api.OsgiService;

import openwis.pilot.common.dto.awisc.LdshIndexDTO;
import openwis.pilot.ldsh.manager.service.LdshIndexService;

@Singleton
@Path("/awisc")
public class AwiscIndexResource {

	private static final Logger logger = Logger.getLogger(AwiscIndexResource.class.getName());

	@Context
	private HttpHeaders headers;

	@Inject
	@OsgiService
	private LdshIndexService ldshIndexService;

	@GET
	@Path("/index")
	@Produces(MediaType.APPLICATION_JSON)
	public LdshIndexDTO index() {
		logger.log(Level.INFO, "Getting Indexing Data....: " );
		return ldshIndexService.getLdshIndexingInformation();
	}

	
	

}
