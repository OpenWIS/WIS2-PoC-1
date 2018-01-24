package openwis.pilot.ldsh.manager.mappers;

import openwis.pilot.ldsh.common.dto.DataFormatDTO;
import openwis.pilot.ldsh.manager.model.DataFormat;

import org.mapstruct.Mapper;

@Mapper
public interface DataFormatMapper {

	DataFormatDTO toDataFormatDTO(DataFormat dataFormat);

	DataFormat toDataFormat(DataFormatDTO dataFromatDto);

}
