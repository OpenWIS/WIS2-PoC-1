package openwis.pilot.awisc.server.manager.mappers;

import org.mapstruct.Mapper;

import openwis.pilot.awisc.server.common.dto.DataFormatDTO;
import openwis.pilot.awisc.server.manager.model.DataFormat;

@Mapper
public interface DataFormatMapper {

	DataFormatDTO toDataFormatDTO(DataFormat dataFormat);

	DataFormat toDataFormat(DataFormatDTO dataFormatDto);
}
