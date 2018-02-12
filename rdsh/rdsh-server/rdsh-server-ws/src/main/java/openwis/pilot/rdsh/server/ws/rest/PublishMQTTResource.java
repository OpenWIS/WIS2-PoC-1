package openwis.pilot.rdsh.server.ws.rest;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import openwis.pilot.common.dto.DatasetMQTTPublishDTO;
import openwis.pilot.rdsh.server.manager.service.LDSHService;
import openwis.pilot.rdsh.server.manager.service.MQTTService;
import org.ops4j.pax.cdi.api.OsgiService;

@Singleton
@Path("/publish")
public class PublishMQTTResource {

  @Inject
  @OsgiService
  private LDSHService ldshService;

  @Inject
  @OsgiService
  private MQTTService mqttService;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response publish(DatasetMQTTPublishDTO datasetMQTTPublishDTO) {
    ResponseBuilder responseBuilder;
System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@ RDHSH");
    // Check that the caller is using a valid token before allowing publication.
    if (!ldshService.validateLDSHToken(datasetMQTTPublishDTO.getToken())) {
      responseBuilder = Response.status(Status.UNAUTHORIZED);
    } else {
      // Proceed to publication.
System.out.println("RDSH :I GOT "+ datasetMQTTPublishDTO);
      mqttService.publish(datasetMQTTPublishDTO);
      responseBuilder = Response.ok();
    }

    return responseBuilder.build();
  }
}
