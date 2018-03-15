package openwis.pilot.ldsh.manager.bean;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.camel.Exchange;
import org.apache.camel.component.file.GenericFile;

import openwis.pilot.ldsh.common.dto.DatasetDTO;
import openwis.pilot.ldsh.manager.service.DatasetService;
import openwis.pilot.ldsh.manager.service.SystemService;

@Singleton
public class BeanFileInputHandler<T> {

	private static final Logger logger = Logger.getLogger(BeanFileInputHandler.class.getName());

	@Inject
	private SystemService systemService;

	@Inject
	private DatasetService datasetService;

	public GenericFile<T> fileParser(GenericFile<T> file, Exchange exchange) {

		logger.log(Level.INFO, "\n***file received " + file.getFileName() + "\n");

		List<DatasetDTO> datasets = datasetService.getAllDataSets();
		for (DatasetDTO dataset : datasets) {

			if (file.getFileName().startsWith(dataset.getFilenameprefix()) && !dataset.getFilenameprefix().isEmpty()) {
				logger.log(Level.INFO, "*** file received " + file.getFileName());
				String sysId = systemService.getAllSystemProperties().getSystemId();
				
				String[] fileNameSplits = file.getFileName().split("\\.");
				// extension is assumed to be the last part
				int extensionIndex = fileNameSplits.length - 1;

				String newName = dataset.getFilenameprefix()+"-"+sysId +"." + fileNameSplits[extensionIndex];
				exchange.getIn().setHeader(Exchange.FILE_NAME, newName );
				
				logger.log(Level.INFO,"*** file renamed as" + newName +"\n");
			}
		}

		return file;
	}

}
