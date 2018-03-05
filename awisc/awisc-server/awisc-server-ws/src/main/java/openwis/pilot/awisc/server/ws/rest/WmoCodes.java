package openwis.pilot.awisc.server.ws.rest;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ops4j.pax.cdi.api.OsgiService;

import openwis.pilot.awisc.server.common.dto.WmoCodeDTO;
import openwis.pilot.awisc.server.manager.service.WmoCodeService;

@Singleton
@Path("/wmo-codes")
public class WmoCodes {

  @OsgiService
  @Inject
  private WmoCodeService wmoCodeService;

  /**
   * Get all wmo codes.
   *
   * @return Returns a JSON representation of all wmo codes
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<WmoCodeDTO> getAllWmoCodes() {
    return wmoCodeService.getWmoCodes();
  }

}
