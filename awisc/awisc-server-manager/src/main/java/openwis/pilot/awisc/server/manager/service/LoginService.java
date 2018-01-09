package openwis.pilot.awisc.server.manager.service;

import openwis.pilot.awisc.server.common.dto.ServerMessage;
import openwis.pilot.awisc.server.common.dto.User;

public interface LoginService {

	public ServerMessage login(User user);
		
}
