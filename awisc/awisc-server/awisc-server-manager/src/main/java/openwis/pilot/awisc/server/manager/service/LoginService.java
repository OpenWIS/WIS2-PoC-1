package openwis.pilot.awisc.server.manager.service;

import openwis.pilot.awisc.server.common.dto.UserDTO;

public interface LoginService {

  UserDTO login(UserDTO userDTO);
}
