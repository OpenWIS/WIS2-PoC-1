package openwis.pilot.awisc.server.manager.mappers;

import org.mapstruct.Mapper;

import openwis.pilot.awisc.server.common.dto.DatasetDTO;
import openwis.pilot.awisc.server.manager.model.Dataset;

@Mapper
public interface DatasetMapper {

	DatasetDTO toDatasetDTO(Dataset dataset);

	Dataset toDataset(DatasetDTO datasetDto);
}
