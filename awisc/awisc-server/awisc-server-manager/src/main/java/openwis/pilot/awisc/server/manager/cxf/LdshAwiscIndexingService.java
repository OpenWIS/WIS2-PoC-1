package openwis.pilot.awisc.server.manager.cxf;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import openwis.pilot.common.dto.awisc.LdshIndexDTO;

@Path("/awisc")
public interface LdshAwiscIndexingService {
	
	@GET
	@Path("/index")
	LdshIndexDTO index();

}
