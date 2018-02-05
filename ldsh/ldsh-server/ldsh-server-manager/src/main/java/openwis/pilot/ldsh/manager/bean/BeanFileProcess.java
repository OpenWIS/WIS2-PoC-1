/**
 * 
 */
package openwis.pilot.ldsh.manager.bean;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

import openwis.pilot.common.dto.DatasetMQTTPublishDTO;
import openwis.pilot.ldsh.common.dto.DatasetDTO;
import openwis.pilot.ldsh.manager.service.DatasetService;
import openwis.pilot.ldsh.manager.service.SystemService;

import org.apache.camel.Exchange;
import org.apache.camel.component.file.GenericFile;

@Singleton
public class BeanFileProcess<T> {

	private static final Logger logger = Logger.getLogger(BeanFileProcess.class
			.getName());

	@Inject
	private SystemService systemService;

	@Inject
	private DatasetService datasetService;
	
	
	public DatasetMQTTPublishDTO fileParser(GenericFile<T> file,
			Exchange exchange) {

		String bodyText = exchange.getIn().getBody(String.class);
		DatasetMQTTPublishDTO datasetMQTTPublishDTO = new DatasetMQTTPublishDTO();

		ArrayList<DatasetDTO> datasets = (ArrayList<DatasetDTO>) datasetService.getAllDataSets();

		for (DatasetDTO dataset : datasets) {

			if (file.getFileName().startsWith(dataset.getFilenameprefix())) {
				logger.log(Level.INFO,"*** Start processing " + file.getFileName());
				
				if (dataset.isRdshDissEnabled()){
				
					if (dataset.isSendData()) {
						datasetMQTTPublishDTO.setBinaryContentBase64(encodeToBase64(bodyText));
					}
				
					datasetMQTTPublishDTO.setTopic("DIMI:TEST_TOPIC"); // systemId/ldsh-dataset-prefix
					datasetMQTTPublishDTO.setDownloadURL(dataset.getDownloadUrl());
					datasetMQTTPublishDTO.setToken(systemService.getRdsh().getToken());
					datasetMQTTPublishDTO.setMessage("TODO MSG");
					// new data available from `System  name`..@ topic
				} else {
					logger.log(Level.INFO,"*** RDSH Dissemination is off for " + dataset.getName());
				} 
			}
		}

		// datasetMQTTPublishDTO.setDownloadURL(downloadURL);
		// Asto pros to paron san ftp url

		// file name
		// + query sta active datasets kai na parw ta prefixes tous, kai koitaw
		// an einai tou onomatos..

		// topic name..
		// - 3/ De xreiazetai na ftiaxnetai kapoio topic, ayto tha ginetai
		// dynamika thn ora toy publishing.
		// Subscription URI px: mqtt://myldshserverip/topicname
		logger.log(Level.INFO, "SENDING to RDSH", datasetMQTTPublishDTO);

		return datasetMQTTPublishDTO;
	}

	private String encodeToBase64(String text) {
		// TODO ???
		String s = new String(Base64.getEncoder().encode(text.getBytes()),
				StandardCharsets.UTF_8);
		return s;
	}

}
