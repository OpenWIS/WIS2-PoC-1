package openwis.pilot.rdsh.server.ws.rest;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import openwis.pilot.common.security.JWTNeeded;
import openwis.pilot.rdsh.server.common.dto.LDSHDTO;
import openwis.pilot.rdsh.server.manager.service.LDSHService;
import org.ops4j.pax.cdi.api.OsgiService;

@Singleton
@Path("/ldsh")
public class LDSHResource {

  @OsgiService
  @Inject
  private LDSHService ldshService;

  /**
   * Get all LDSHs registered in the system.
   *
   * @return Returns a JSON representation of all LDSHs as
   * {@link openwis.pilot.rdsh.server.common.dto.LDSHDTO}.
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAllLDSH() {
    return Response.ok(ldshService.getLDSH()).build();
  }

  /**
   * Fetches a specific LDSH by Id.
   * @param ldshId The Id of the LDSH to fetch.
   * @return Returns a JSON representation of {@link openwis.pilot.rdsh.server.common.dto.LDSHDTO}
   * or an empty reply.
   */
  @GET
  @JWTNeeded
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getLDSH(@PathParam("id") String ldshId) {
    return Response.ok(ldshService.getLDSH(ldshId)).build();
  }

  /**
   * Saves or creates an LDSH.
   * @param ldshDTO The details of the LDSH to save or create.
   * @return The Id of the newly created or saved LDSH.
   */
  @POST
  @JWTNeeded
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response saveLDSH(LDSHDTO ldshDTO) {
    return Response.ok(ldshService.saveLDSH(ldshDTO)).build();
  }

  /**
   * Deletes a specific LDSH by Id.
   * @param ldshId The Id of the LDSH to delete.
   */
  @DELETE
  @JWTNeeded
  @Path("{id}")
  public Response deleteLDSH(@PathParam("id") String ldshId) {
    ldshService.deleteLDSH(ldshId);
    return Response.noContent().build();
  }

  /**
   * Checks if a given LDSH token is valid or not.
   * @param ldshToken The LDSH token to check for validity.
   * @return Returns 204 when the token is valid, or 401 otherwise.
   */
  @GET
  @Path("token/{id}")
  public Response validateToken(@PathParam("id")String ldshToken) {
    if (ldshService.validateLDSHToken(ldshToken)) {
      return Response.noContent().build();
    } else {
      return Response.status(Status.UNAUTHORIZED).build();
    }
  }

}
