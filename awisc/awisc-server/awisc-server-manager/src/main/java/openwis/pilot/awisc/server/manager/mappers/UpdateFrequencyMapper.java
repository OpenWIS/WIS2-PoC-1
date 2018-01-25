package openwis.pilot.awisc.server.manager.mappers;

import org.mapstruct.Mapper;

import openwis.pilot.awisc.server.common.dto.UpdateFrequencyDTO;
import openwis.pilot.awisc.server.manager.model.UpdateFrequency;

@Mapper
public interface UpdateFrequencyMapper {
	
	UpdateFrequencyDTO toUpdateFrequencyDTO(UpdateFrequency updateFrequency);
	
	UpdateFrequency toUpdateFrequency(UpdateFrequencyDTO  updateFrequencyDto);
}
