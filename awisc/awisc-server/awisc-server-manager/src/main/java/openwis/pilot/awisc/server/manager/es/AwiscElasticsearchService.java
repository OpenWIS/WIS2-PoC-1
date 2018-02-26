package openwis.pilot.awisc.server.manager.es;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface AwiscElasticsearchService {
	
	@POST
	@Path("/{index}/_search")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	String search(@PathParam("index") String index, String query);

}
