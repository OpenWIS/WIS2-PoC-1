package openwis.pilot.ldsh.manager.mappers;

import openwis.pilot.ldsh.common.dto.UserDTO;
import openwis.pilot.ldsh.manager.model.User;

import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
	  UserDTO toUserDTO(User user);
}
