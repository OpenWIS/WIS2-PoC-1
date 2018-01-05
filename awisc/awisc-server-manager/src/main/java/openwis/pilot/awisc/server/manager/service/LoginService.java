package openwis.pilot.awisc.server.manager.service;

import openwis.pilot.awisc.server.common.dto.ServerMessage;

public interface LoginService {

	public ServerMessage login(openwis.pilot.awisc.server.common.dto.User user);
		
}
