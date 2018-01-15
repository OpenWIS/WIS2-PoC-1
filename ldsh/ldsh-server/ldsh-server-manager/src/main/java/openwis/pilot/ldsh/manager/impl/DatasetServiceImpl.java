package openwis.pilot.ldsh.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

import openwis.pilot.ldsh.db.dao.GenericDao;
import openwis.pilot.ldsh.db.dao.IDaoFactory;
import openwis.pilot.ldsh.db.model.Country;
import openwis.pilot.ldsh.db.model.DataFormat;
import openwis.pilot.ldsh.db.model.Dataset;
import openwis.pilot.ldsh.dto.CountryDTO;
import openwis.pilot.ldsh.dto.DataFormatDTO;
import openwis.pilot.ldsh.dto.DatasetDTO;
import openwis.pilot.ldsh.manager.service.DatasetService;

import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;
import org.ops4j.pax.cdi.api.OsgiService;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;

@Singleton
@OsgiServiceProvider(classes = { DatasetService.class })
public class DatasetServiceImpl implements DatasetService {

	@OsgiService
	@Inject
	private IDaoFactory iDaoFactory;

	private GenericDao<Dataset> datasetDAO;
	private GenericDao<DataFormat> dataFormatDAO;
	private GenericDao<Country> countryDAO;
	private Mapper mapper = DozerBeanMapperBuilder.buildDefault();

	private static final Logger logger = Logger
			.getLogger(DatasetServiceImpl.class.getName());

	public DatasetDTO saveDataset(DatasetDTO datasetDTO) {
System.out.println("INCOMMING "+ datasetDTO.toString());
		datasetDAO = iDaoFactory.getDao(Dataset.class);
		Mapper mapper = DozerBeanMapperBuilder.buildDefault();
		Dataset dataset = mapper.map(datasetDTO, Dataset.class);

		try {

			Dataset ds = datasetDAO.get(dataset.getId());
			if (ds != null) {
				System.out.println("UPDATE "+ dataset.toString());
				datasetDAO.update(dataset);
			} else {
				System.out.println("SAVE"+ dataset.toString());
				datasetDAO.create(dataset);
			}

		} catch (Exception e) {
			logger.log(Level.SEVERE, "error", e);
			e.printStackTrace();
		}
		return datasetDTO;
	}

	public DatasetDTO getDataSet(long id) {

		datasetDAO = iDaoFactory.getDao(Dataset.class);
		Dataset dataset = datasetDAO.get(id);
		if (dataset == null) {
			System.out.println("NO DATASET FOUND");
			return null;
		}
		Mapper mapper = DozerBeanMapperBuilder.buildDefault();
		DatasetDTO datasetDTO = mapper.map(dataset, DatasetDTO.class);

		return datasetDTO;
	}

	@Override
	public List<DatasetDTO>  getAllDataSets() {
		datasetDAO = iDaoFactory.getDao(Dataset.class);
		ArrayList<Dataset> datasets = (ArrayList<Dataset>) datasetDAO.list();
System.out.println("DASETS I got " + datasets.size());

		Mapper mapper = DozerBeanMapperBuilder.buildDefault();
		ArrayList<DatasetDTO> datasetList = new ArrayList<DatasetDTO>();

		for (int i = 0; i < datasets.size(); i++) {

			DatasetDTO datasetDTO = mapper.map(datasets.get(i),DatasetDTO.class);
			datasetList.add(datasetDTO);
		}
		return datasetList;
	}

	@Override
	public List<DataFormatDTO> getDataFormats() {
		dataFormatDAO = iDaoFactory.getDao(DataFormat.class);
		ArrayList<DataFormat> dataFormats = (ArrayList<DataFormat>) dataFormatDAO.list();
System.out.println("DATAFORMATS I got " + dataFormats.size());
//		Mapper mapper = DozerBeanMapperBuilder.buildDefault();
		ArrayList<DataFormatDTO> dataFormatsList = new ArrayList<DataFormatDTO>();

		for (int i = 0; i < dataFormats.size(); i++) {
			DataFormatDTO dataformatDTO = mapper.map(dataFormats.get(i),
					DataFormatDTO.class);
			dataFormatsList.add(dataformatDTO);
		}
		return dataFormatsList;
	}

	@Override
	public List<CountryDTO> getCountries() {


		countryDAO = iDaoFactory.getDao(Country.class);
		ArrayList<Country> countries = (ArrayList<Country>) countryDAO.list();
System.out.println("DASETS I got " + countries.size());

//		Mapper mapper = DozerBeanMapperBuilder.buildDefault();
		ArrayList<CountryDTO> countrytList = new ArrayList<CountryDTO>();
		for (int i = 0; i < countries.size(); i++) {
			CountryDTO countryDTO = mapper.map(countries.get(i),CountryDTO.class);
			countrytList.add(countryDTO);
		}
		return countrytList;
	}
}
