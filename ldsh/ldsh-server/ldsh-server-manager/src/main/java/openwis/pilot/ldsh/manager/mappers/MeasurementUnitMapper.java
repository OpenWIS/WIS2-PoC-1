package openwis.pilot.ldsh.manager.mappers;

import openwis.pilot.ldsh.common.dto.FrequencyUnitDTO;
import openwis.pilot.ldsh.manager.model.FrequencyUnit;

import org.mapstruct.Mapper;

@Mapper
public interface MeasurementUnitMapper {

	FrequencyUnitDTO toMeasurementUnitDTO(FrequencyUnit mu);

	FrequencyUnit toMeasurementUnit(FrequencyUnitDTO mu);

}
