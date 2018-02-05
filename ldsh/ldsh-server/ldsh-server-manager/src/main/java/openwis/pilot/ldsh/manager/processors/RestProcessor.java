package openwis.pilot.ldsh.manager.processors;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Singleton;
import javax.xml.stream.XMLStreamException;

import openwis.pilot.common.dto.DatasetMQTTPublishDTO;

import org.apache.camel.Converter;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;

@Singleton
public class RestProcessor {
	
	private static final Logger logger = Logger.getLogger(RestProcessor.class.getName());
	

	@EndpointInject(uri = "direct:rest.prep")
	@Converter
	public DatasetMQTTPublishDTO handle(Exchange exchange) throws IOException, XMLStreamException, InterruptedException {
		DatasetMQTTPublishDTO datasetMQTTPublishDTO = null;
		try {
			
			datasetMQTTPublishDTO = (DatasetMQTTPublishDTO) exchange.getIn().getBody(DatasetMQTTPublishDTO.class);

		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}

		return datasetMQTTPublishDTO;
	}
}
