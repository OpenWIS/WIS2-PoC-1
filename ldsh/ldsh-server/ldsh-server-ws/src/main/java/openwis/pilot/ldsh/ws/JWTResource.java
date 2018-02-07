package openwis.pilot.ldsh.ws;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import openwis.pilot.common.dto.JwtDTO;
import org.ops4j.pax.cdi.api.OsgiService;
import openwis.pilot.ldsh.manager.service.JWTService; 
import openwis.pilot.ldsh.common.dto.UserDTO;

@Singleton
@Path("/jwt")
public class JWTResource {

  @Inject
  @OsgiService
  private JWTService jwtService;

  /**
   * Authenticates a user and returns a JWT if authentication was successful.
   * @param userDTO The username and password of the user to authenticate.
   * @return A JWT token.
   */
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response authenticate(UserDTO userDTO) {

    try {
      JwtDTO jwtDTO = new JwtDTO(jwtService.authenticate(userDTO.getUsername(), userDTO.getPassword()));
      return Response.ok(jwtDTO).build();
    } catch (SecurityException e) {
      return Response.status(Status.UNAUTHORIZED).build();
    }
  }
}
