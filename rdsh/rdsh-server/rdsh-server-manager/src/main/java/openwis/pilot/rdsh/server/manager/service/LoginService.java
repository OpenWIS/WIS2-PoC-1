package openwis.pilot.rdsh.server.manager.service;

import openwis.pilot.rdsh.server.common.dto.UserDTO;

public interface LoginService {

  UserDTO login(UserDTO userDTO);
}
