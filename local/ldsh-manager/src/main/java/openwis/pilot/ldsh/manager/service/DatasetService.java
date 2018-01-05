package openwis.pilot.ldsh.manager.service;

import java.util.List;

import openwis.pilot.ldsh.dto.DatasetDTO;


public interface DatasetService {

	public DatasetDTO saveDataset(DatasetDTO dataset); 
	public DatasetDTO getDataSet(long id); 
	public List<DatasetDTO> getAllDataSets();

}
