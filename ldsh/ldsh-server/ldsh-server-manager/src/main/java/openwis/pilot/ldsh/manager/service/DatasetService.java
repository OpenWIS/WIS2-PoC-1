package openwis.pilot.ldsh.manager.service;

import java.util.List;

import openwis.pilot.ldsh.common.dto.CountryDTO;
import openwis.pilot.ldsh.common.dto.DataFormatDTO;
import openwis.pilot.ldsh.common.dto.DatasetDTO;
import openwis.pilot.ldsh.common.dto.FrequencyUnitDTO;
import openwis.pilot.ldsh.common.dto.WmoCodeDTO;



public interface DatasetService {

	public DatasetDTO getDataSet(Long id); 
	public DatasetDTO saveDataset(DatasetDTO dataset); 
	public Boolean deleteDataset(Long id);
	public List<DatasetDTO> getAllDataSets();
	public List<DataFormatDTO> getDataFormats();
	public List<CountryDTO> getCountries();
	public List<WmoCodeDTO> getWmoCodes();
	public List<FrequencyUnitDTO> getMeasurementUnits();
	public void updateDatasetLastUpdate(Long id);
	public boolean verifyRelativeUrl(String url);

}
