package openwis.pilot.awisc.server.manager.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import openwis.pilot.awisc.server.common.dto.WmoCodeDTO;
import openwis.pilot.awisc.server.manager.model.WmoCode;

@Mapper
public interface WmoCodeMapper {
	
	WmoCodeDTO toWmoCodeDTO(WmoCode wmoCode);
	
	WmoCode toWmoCode(WmoCodeDTO  wmoCodeDto);
	
	List<WmoCodeDTO> toWmoCodeDTOList(List<WmoCode> wmoCodes);
}
