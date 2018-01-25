package openwis.pilot.awisc.server.ws.rest;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import openwis.pilot.awisc.server.common.dto.UserDTO;
import openwis.pilot.awisc.server.common.exception.ServiceException;
import openwis.pilot.awisc.server.manager.service.LoginService;
import org.ops4j.pax.cdi.api.OsgiService;

@Singleton
public class HelloWorldResource {

  @OsgiService @Inject
  private LoginService loginService;

  // Demo OK response.
  @GET
  @Path("/hello-world")
  @Produces(MediaType.APPLICATION_JSON)
  public Response hw1() {
    return Response.ok("{\"message\": \"test-plain-json\"}").build();
  }

  // Demo custom exception, hiding real internal error with a generic HTTP status 500.
  @GET
  @Path("/hello-world-error")
  @Produces(MediaType.APPLICATION_JSON)
  public Response hw2() {
    return Response.ok(1/0).build();
  }

  // Demo for QueryDSL + MapStruct.
  @GET
  @Path("/hello-world-qdsl")
  @Produces(MediaType.APPLICATION_JSON)
  public UserDTO hw3() {
    try {
		return loginService.login(new UserDTO("user1", "pass1"));
	} catch (ServiceException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return null;
  }

}
