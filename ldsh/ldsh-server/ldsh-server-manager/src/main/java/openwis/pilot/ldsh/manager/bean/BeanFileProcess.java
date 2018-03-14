/**
 * 
 */
package openwis.pilot.ldsh.manager.bean;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

import openwis.pilot.common.dto.DatasetMQTTPublishDTO;
import openwis.pilot.ldsh.common.dto.DatasetDTO;
import openwis.pilot.ldsh.common.dto.RemoteSystemDTO;
import openwis.pilot.ldsh.manager.service.DatasetService;
import openwis.pilot.ldsh.manager.service.SystemService;

import org.apache.camel.Exchange;
import org.apache.camel.component.file.GenericFile;

@Singleton
public class BeanFileProcess<T> {

	private static final Logger logger = Logger.getLogger(BeanFileProcess.class.getName());

	@Inject
	private SystemService systemService;

	@Inject
	private DatasetService datasetService;
	
	
	public DatasetMQTTPublishDTO fileParser(GenericFile<T> file, Exchange exchange) {

		String bodyText = exchange.getIn().getBody(String.class);
		DatasetMQTTPublishDTO datasetMQTTPublishDTO = null;

		List<DatasetDTO> datasets = datasetService.getAllDataSets();
		for (DatasetDTO dataset : datasets) {

			if (file.getFileName().startsWith(dataset.getFilenameprefix())&& !dataset.getFilenameprefix().isEmpty()  ) {
				logger.log(Level.INFO,"*** Start processing " + file.getFileName());
				if (dataset.isRdshDissEnabled()){
					datasetMQTTPublishDTO = new DatasetMQTTPublishDTO();

					if (dataset.isSendData()) {
						datasetMQTTPublishDTO.setBinaryContentBase64(encodeToBase64(bodyText));
					}
				
					String sysId = systemService.getAllSystemProperties().getSystemId();

					RemoteSystemDTO rdsh = systemService.getRdsh();
					
					// {RDSH URL}/{system id}/{relative}/prefix
					String topic = rdsh.getUrl() + "/" + sysId + "/" +dataset.getRelativeUrl() +  "/" +dataset.getFilenameprefix();
					datasetMQTTPublishDTO.setTopic(topic); 
					datasetMQTTPublishDTO.setDownloadURL(dataset.getDownloadUrl());
					datasetMQTTPublishDTO.setToken(systemService.getRdsh().getToken());
					datasetMQTTPublishDTO.setDatasetName(dataset.getName());
					datasetMQTTPublishDTO.setMessage("New data available from "+systemService.getAllSystemProperties().getTitle() +" @"+topic);

					exchange.getOut().setHeader("destinationUrl", systemService.getRdsh().getUrl().replace("https://", "")+"/cxf/rdsh-api/publish");
					exchange.getOut().setHeader("ValidLdshMessage", true);
					
					datasetService.updateDatasetLastUpdate(dataset.getId());
	                break;
				} else {
					exchange.getOut().setHeader("ValidLdshMessage", false);
					logger.log(Level.WARNING,"*** RDSH Dissemination is off for " + dataset.getName());
					return null;
				}
			}
		}
		if (datasetMQTTPublishDTO == null){
			logger.log(Level.INFO,"*** No matching Dataset found for " + file.getFileName());
			exchange.getOut().setHeader("ValidLdshMessage", false);
			return null;
		} 
		logger.log(Level.INFO, "*** Sending to RDSH ", datasetMQTTPublishDTO.getMessage());
		return datasetMQTTPublishDTO;
	}


	private String encodeToBase64(String text) {
		String s = new String(Base64.getEncoder().encode(text.getBytes()), StandardCharsets.UTF_8);
		return s;
	}

}
