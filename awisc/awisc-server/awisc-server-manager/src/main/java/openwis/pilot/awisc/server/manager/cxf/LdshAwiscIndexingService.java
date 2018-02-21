package openwis.pilot.awisc.server.manager.cxf;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import openwis.pilot.common.dto.awisc.LdshIndexDTO;

@Path("/awisc")
public interface LdshAwiscIndexingService {
	
	@GET
	@Path("/index")
	@Produces(MediaType.APPLICATION_JSON)
	LdshIndexDTO index();

}
