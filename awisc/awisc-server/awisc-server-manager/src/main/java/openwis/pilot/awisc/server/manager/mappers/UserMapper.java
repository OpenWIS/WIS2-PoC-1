package openwis.pilot.awisc.server.manager.mappers;

import openwis.pilot.awisc.server.common.dto.UserDTO;
import openwis.pilot.awisc.server.manager.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
  UserDTO toUserDTO(User user);
}
