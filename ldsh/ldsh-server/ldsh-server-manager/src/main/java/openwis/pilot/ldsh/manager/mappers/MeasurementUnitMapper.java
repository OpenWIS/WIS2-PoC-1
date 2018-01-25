package openwis.pilot.ldsh.manager.mappers;

import openwis.pilot.ldsh.common.dto.MeasurementUnitDTO;
import openwis.pilot.ldsh.manager.model.MeasurementUnit;

import org.mapstruct.Mapper;

@Mapper
public interface MeasurementUnitMapper {

	MeasurementUnitDTO toMeasurementUnitDTO(MeasurementUnit mu);

	MeasurementUnit toMeasurementUnit(MeasurementUnitDTO mu);

}
