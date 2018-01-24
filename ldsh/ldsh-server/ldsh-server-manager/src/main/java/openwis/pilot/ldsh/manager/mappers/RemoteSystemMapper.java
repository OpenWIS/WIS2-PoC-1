package openwis.pilot.ldsh.manager.mappers;

import openwis.pilot.ldsh.common.dto.RemoteSystemDTO;
import openwis.pilot.ldsh.manager.model.RemoteSystem;

import org.mapstruct.Mapper;

@Mapper
public interface RemoteSystemMapper {

	RemoteSystemDTO toRemoteSystemDTO(RemoteSystem rs);

	RemoteSystem toRemoteSystem(RemoteSystemDTO rsDto);

}
