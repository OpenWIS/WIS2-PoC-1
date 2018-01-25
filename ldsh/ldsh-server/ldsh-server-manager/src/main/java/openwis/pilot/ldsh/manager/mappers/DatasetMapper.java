package openwis.pilot.ldsh.manager.mappers;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

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
				 datasetDto.getPeriodTo(),
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
	
	private Set<WmoCode> getWmoCodes(Set<WmoCodeDTO> codesList) {
		
		if (codesList == null) {
			return Collections.emptySet();
		}

		Set<WmoCode> set = new LinkedHashSet<WmoCode>();
		for (WmoCodeDTO wmocode : codesList) {
			set.add(wmoCodeMapper.toWmoCode(wmocode));
		}

		return set;
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
				 dataset.getPeriodTo(),
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
	
	private Set<WmoCodeDTO> getWmoCodesDTO(Set<WmoCode> codesList) {
		
		if (codesList == null) {
			return Collections.emptySet();
		}
		
		Set<WmoCodeDTO> set = new LinkedHashSet<WmoCodeDTO>();
		for (WmoCode wmocode :codesList){
			set.add(wmoCodeMapper.toWmoCodeDTO(wmocode));
		}
	
		return set;
	}
	
}
