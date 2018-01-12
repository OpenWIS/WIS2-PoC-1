package openwis.pilot.awisc.server.manager.service;

import openwis.pilot.awisc.server.common.dto.ServiceMessage;
import openwis.pilot.awisc.server.common.dto.User;

public interface LoginService {

	public ServiceMessage login(User user);
		
}
