package openwis.pilot.ldsh.manager.service;

import java.util.List;

import openwis.pilot.ldsh.common.dto.CountryDTO;
import openwis.pilot.ldsh.common.dto.DataFormatDTO;
import openwis.pilot.ldsh.common.dto.DatasetDTO;



public interface DatasetService {

	public DatasetDTO getDataSet(long id); 
	public DatasetDTO saveDataset(DatasetDTO dataset); 
	public List<DatasetDTO> getAllDataSets();
	public List<DataFormatDTO> getDataFormats();
	public List<CountryDTO> getCountries();

}
