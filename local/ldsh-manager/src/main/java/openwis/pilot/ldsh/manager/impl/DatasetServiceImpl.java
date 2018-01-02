package openwis.pilot.ldsh.manager.impl;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
		System.out.println("dao factory created...");

		Dataset dataset = new Dataset();
		// TODO add mapper!!!
		dataset.setId(datasetDTO.getId());
		dataset.setName(datasetDTO.getName());
		dataset.setCountry(datasetDTO.getCountry());
		dataset.setDescription(datasetDTO.getDescription());
		dataset.setCity(datasetDTO.getCity());
		dataset.setFilenameprefix(datasetDTO.getFilenameprefix());

		try {
System.out.println("SAVing " + dataset);

			datasetDAO.create(dataset);
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
		if (dataset == null ){
System.out.println("NO DATASET FOUND");
			return null;
		}
		// TODO MAPPER
		DatasetDTO datasetDTO = new DatasetDTO();
		datasetDTO.setId(dataset.getId());
		datasetDTO.setName(dataset.getName());
		datasetDTO.setCountry(dataset.getCountry());
		datasetDTO.setDescription(dataset.getDescription());
		datasetDTO.setCity(dataset.getCity());
		datasetDTO.setFilenameprefix(dataset.getFilenameprefix());

		return datasetDTO;
	}

}
