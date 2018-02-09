package openwis.pilot.awisc.server.manager.service;

import openwis.pilot.common.dto.awisc.LdshIndexDTO;

public interface AwiscIndexingService {
	
	/**
	 * Indexes the LDSH information
	 * @param dto The LDSH information
	 */
	void index(LdshIndexDTO dto);

}
