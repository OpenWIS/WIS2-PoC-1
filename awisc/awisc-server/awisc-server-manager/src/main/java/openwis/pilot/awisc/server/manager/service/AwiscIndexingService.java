package openwis.pilot.awisc.server.manager.service;

import java.io.UnsupportedEncodingException;

import openwis.pilot.awisc.server.common.dto.LdshDTO;
import openwis.pilot.common.dto.awisc.LdshIndexDTO;

public interface AwiscIndexingService {
	
	/**
	 * Indexes the LDSH information
	 * @param dto The LDSH information
	 */
	void index(LdshIndexDTO dto) throws UnsupportedEncodingException;
	
	/**
	 * Calls the AWISC-related REST service of the LDSH and retrieves indexing information 
	 * @param ldsh the LDSH that will be called
	 * @return
	 */
	LdshIndexDTO getLdshIndexDTO(LdshDTO ldsh);
	
	/**
	 * Deletes an LDSH entry from the elasticsearch index. Used when deleting the LDSH from AWISC
	 * @param systemId
	 * @return
	 * @throws Exception
	 */
	void deleteLdsh(String systemId);

}
