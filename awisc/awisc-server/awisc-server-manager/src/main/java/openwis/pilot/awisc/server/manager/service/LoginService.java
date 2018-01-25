package openwis.pilot.awisc.server.manager.service;

import openwis.pilot.awisc.server.common.dto.UserDTO;
import openwis.pilot.awisc.server.common.exception.ServiceException;

public interface LoginService {

  UserDTO login(UserDTO userDTO) throws ServiceException;
}
