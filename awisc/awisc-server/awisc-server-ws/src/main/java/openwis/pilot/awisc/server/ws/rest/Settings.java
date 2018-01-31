package openwis.pilot.awisc.server.ws.rest;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ops4j.pax.cdi.api.OsgiService;

import openwis.pilot.awisc.server.common.dto.ServiceMessage;
import openwis.pilot.awisc.server.common.dto.SettingDTO;
import openwis.pilot.awisc.server.common.util.Constants;
import openwis.pilot.awisc.server.manager.service.SettingsService;
import openwis.pilot.common.security.JWTNeeded;

@Singleton
@Path("/settings")
public class Settings {

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
  @JWTNeeded
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAllSettings() {
    return Response.ok(settingsService.getSettings()).build();
  }

  /**
   * Saves all settings.
   * @param settings The list of settings to save.
   */
  @POST
  @JWTNeeded
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ServiceMessage saveSettings(List<SettingDTO> settings) {
    settingsService.saveSettings(settings);

    return new ServiceMessage(Constants.MessageCode.SETTINGS_SAVE_SUCCESS);
  }

}
