package openwis.pilot.ldsh.manager.impl;

import java.io.File;
import java.io.IOException;
import java.util.Dictionary;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.ops4j.pax.cdi.api.OsgiService;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

import openwis.pilot.ldsh.manager.service.DatasetService;
import openwis.pilot.ldsh.manager.service.FileService;

@Singleton
@OsgiServiceProvider(classes = { FileService.class })
public class FileServiceImpl implements FileService {

	@OsgiService
	@Inject
	private ConfigurationAdmin configAdmin;

	@Inject
	private DatasetService datasetService;

	private static final String SOURCE_FILE_PATH = "trg_file";
	private static final String CONFIG_ADMIN_PID = "openwis.ldsh";
	private static final Logger logger = Logger.getLogger(FileServiceImpl.class.getName());

	@Override
	public File getFile(String relativeUrl, String prefix) {
		logger.log(Level.INFO, "Request for file with prefix:"+ prefix);

//		if (datasetService.verifyRelativeUrl(relativeUrl)) {
			String path = getDownloadFolder();
		
			logger.log(Level.INFO, "Path found: "+ path);
			return findFile(path, prefix);


	}

	private File findFile(String path, String prefix) {

		final File folder = new File(path);
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.getName().startsWith(prefix)) {
				logger.log(Level.INFO, "Found file :", fileEntry.getName());

				return fileEntry;
			}
		}
		logger.log(Level.WARNING, "File not found with prefix:"+ prefix +" at "+ SOURCE_FILE_PATH);
		return null;
	}

	private String getDownloadFolder() {
		try {
			Configuration conf = configAdmin.getConfiguration(CONFIG_ADMIN_PID);
			@SuppressWarnings("unchecked")
			Dictionary<String, Object> props = conf.getProperties();
			return props.get(SOURCE_FILE_PATH).toString();

		} catch (IOException e) {
			logger.log(Level.SEVERE, "Could not parse PID.", e);
		}
		return "";
	}
}
