package openwis.pilot.ldsh.manager.mappers;

import openwis.pilot.ldsh.common.dto.WmoCodeDTO;
import openwis.pilot.ldsh.manager.model.WmoCode;

import org.mapstruct.Mapper;

@Mapper
public interface WmoCodeMapper {

	WmoCodeDTO toWmoCodeDTO(WmoCode wmocode);
	WmoCode toWmoCode (WmoCodeDTO wmoCodeDTO);
	
}
