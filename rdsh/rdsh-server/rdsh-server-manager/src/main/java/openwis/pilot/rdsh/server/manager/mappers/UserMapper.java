package openwis.pilot.rdsh.server.manager.mappers;

import openwis.pilot.rdsh.server.common.dto.UserDTO;
import openwis.pilot.rdsh.server.manager.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
  UserDTO toUserDTO(User user);
}
