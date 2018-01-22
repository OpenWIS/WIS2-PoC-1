package openwis.pilot.ldsh.manager.mappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import openwis.pilot.ldsh.common.dto.DatasetDTO;
import openwis.pilot.ldsh.common.dto.WmoCodeDTO;
import openwis.pilot.ldsh.manager.model.Dataset;
import openwis.pilot.ldsh.manager.model.WmoCode;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { DatasetDTO.class })
public abstract class DatasetMapper {

	private final CountryMapper countryMapper = Mappers.getMapper(CountryMapper.class);
	private final DataFormatMapper dataFormatMapper = Mappers.getMapper(DataFormatMapper.class);
	private final WmoCodeMapper wmoCodeMapper = Mappers.getMapper(WmoCodeMapper.class);
	
	public Dataset toDataset(DatasetDTO datasetDto){
		 
		 if (datasetDto == null){
			 return null;
		 }
		 
		 Dataset dataset = new Dataset(
				 datasetDto.getId(),
				 datasetDto.getName(),
				 datasetDto.getDescription(),
				 datasetDto.getPeriodFrom(),
				 datasetDto.getPeriodFrom(),
				 datasetDto.getLicense(),
				 datasetDto.getImageUrl(),
				 datasetDto.getMeasurementUnit(),
				 getWmoCodes( datasetDto.getWmoCodes()), 
				 countryMapper.toCountry(datasetDto.getCountry()), 
				 datasetDto.getState(),
				 datasetDto.getCity(),
				 datasetDto.getLatitude(),
				 datasetDto.getLongitude(),
				 datasetDto.getElevation(),
				 datasetDto.getRelativeUrl(),
				 datasetDto.getFilenameprefix(),
				 datasetDto.getDownloadUrl(),
				 datasetDto.getSubscriptionUri(),
				 dataFormatMapper.toDataFormat(datasetDto.getDataformat()),
				 datasetDto.isRdshDissEnabled(), 
				 datasetDto.getJsonLd());
				 
		 return dataset;
	 }
	
	private List<WmoCode> getWmoCodes(List<WmoCodeDTO> codesList) {
		
		if (codesList == null) {
			return Collections.emptyList();
		}

		List<WmoCode> list = new ArrayList<WmoCode>();
		for (WmoCodeDTO wmocode : codesList) {
			list.add(wmoCodeMapper.toWmoCode(wmocode));
		}

		return list;
	}
	
		
	public DatasetDTO toDatasetDTO(Dataset dataset){
		 
		 if (dataset == null){
			 return null;
		 }
		 DatasetDTO datasetDto = new DatasetDTO(
				 dataset.getId(),
				 dataset.getName(),
				 dataset.getDescription(),
				 dataset.getPeriodFrom(),
				 dataset.getPeriodFrom(),
				 dataset.getLicense(),
				 dataset.getImageUrl(),
				 dataset.getMeasurementUnit(),
				 getWmoCodesDTO( dataset.getWmoCodes()), // List<WmoCodeDTO> wmoCodes handle below.
				 countryMapper.toCountryDTO(dataset.getCountry()), 
				 dataset.getState(),
				 dataset.getCity(),
				 dataset.getLatitude(),
				 dataset.getLongitude(),
				 dataset.getElevation(),
				 dataset.getRelativeUrl(),
				 dataset.getFilenameprefix(),
				 dataset.getDownloadUrl(),
				 dataset.getSubscriptionUri(),
				 dataFormatMapper.toDataFormatDTO(dataset.getDataformat()),
				 dataset.isRdshDissEnabled(),
				 dataset.getJsonLd());
				 
		 return datasetDto;
	 }
	
	private List<WmoCodeDTO> getWmoCodesDTO(List<WmoCode> codesList) {
		
		if (codesList == null) {
			return Collections.emptyList();
		}
		
		List<WmoCodeDTO> list = new ArrayList<WmoCodeDTO>();
		for (WmoCode wmocode :codesList){
			list.add(wmoCodeMapper.toWmoCodeDTO(wmocode));
		}
	
		return list;
	}
	
}
