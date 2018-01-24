package openwis.pilot.rdsh.server.ws.rest;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import openwis.pilot.rdsh.server.common.dto.SettingDTO;
import openwis.pilot.rdsh.server.manager.service.SettingsService;
import org.ops4j.pax.cdi.api.OsgiService;

import java.util.List;

@Singleton
@Path("/settings")
public class SettingsResource {

  @OsgiService
  @Inject
  private SettingsService settingsService;

  /**
   * Get all settings.
   *
   * @return Returns a JSON representation of all settings as
   * {@link openwis.pilot.rdsh.server.common.dto.SettingDTO}.
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAllSettings() {
    return Response.ok(settingsService.getSettings()).build();
  }

  /**
   * Saves all settings.
   * @param settings The list of settings to save.
   */
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response saveSettings(List<SettingDTO> settings) {
    settingsService.saveSettings(settings);

    return Response.noContent().build();
  }

}
