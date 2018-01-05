package openwis.pilot.ldsh.manager.service;

import openwis.pilot.ldsh.dto.DatasetDTO;


public interface DatasetService {

	public DatasetDTO saveDataset(DatasetDTO dataset); 
	public DatasetDTO getDataSet(long id); 

}
