package openwis.pilot.awisc.server.manager.mappers;

import org.mapstruct.Mapper;

import openwis.pilot.awisc.server.common.dto.LdshDTO;
import openwis.pilot.awisc.server.manager.model.Ldsh;

@Mapper
public interface LdshMapper {

	LdshDTO toLdshDTO(Ldsh ldsh);

	Ldsh toLdsh(LdshDTO ldshDto);
}
