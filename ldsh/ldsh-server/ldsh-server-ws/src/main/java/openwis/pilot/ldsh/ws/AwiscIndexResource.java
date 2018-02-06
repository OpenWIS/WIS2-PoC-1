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
import javax.ws.rs.core.Response;

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.ops4j.pax.cdi.api.OsgiService;

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
	@CrossOriginResourceSharing(allowAllOrigins = true)
	@Produces(MediaType.APPLICATION_JSON)
	public Response index() {
		logger.log(Level.INFO, "Getting Indexing Data....: " );
		return Response.ok(ldshIndexService.getLdshIndexingInformation()).build();
	}

	
	

}
