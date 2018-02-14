package openwis.pilot.awisc.server.manager.service;

import java.util.List;

import openwis.pilot.awisc.server.common.dto.WmoCodeDTO;


public interface WmoCodeService {
	
	
	/**
	 * @return the WMO codes
	 */
	List<WmoCodeDTO> getWmoCodes();

}
