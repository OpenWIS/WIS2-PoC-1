package openwis.pilot.ldsh.manager.impl;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import openwis.pilot.ldsh.db.dao.GenericDao;
import openwis.pilot.ldsh.db.dao.IDaoFactory;
import openwis.pilot.ldsh.db.model.Dataset;
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

	// private DatasetDTO datasetDTO;
	private GenericDao<Dataset> datasetDAO;

	private static final Logger logger = Logger.getLogger(DatasetServiceImpl.class.getName());

	public DatasetDTO saveDataset(DatasetDTO datasetDTO) {

		datasetDAO = iDaoFactory.getDao(Dataset.class);

		Mapper mapper = DozerBeanMapperBuilder.buildDefault();
		Dataset dataset = mapper.map(datasetDTO, Dataset.class);

		try {

			Dataset ds = datasetDAO.get(dataset.getId());
			System.out.println(ds.toString());
			if (ds != null) {	
				datasetDAO.update(dataset);
			} else {
				datasetDAO.create(dataset);
			}

			System.out.println("saved.");

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
		// TODO MAPPER

		Mapper mapper = DozerBeanMapperBuilder.buildDefault();
		DatasetDTO datasetDTO = mapper.map(dataset, DatasetDTO.class);

		return datasetDTO;
	}

}
