package openwis.pilot.ldsh.manager.service;

import openwis.pilot.common.dto.awisc.LdshIndexDTO;

public interface LdshIndexService {
	
	public LdshIndexDTO getLdshIndexingInformation(String baseUrl);

}
