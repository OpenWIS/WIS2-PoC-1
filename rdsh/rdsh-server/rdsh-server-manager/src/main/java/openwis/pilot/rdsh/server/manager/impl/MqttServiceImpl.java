package openwis.pilot.rdsh.server.manager.impl;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.inject.Singleton;

import openwis.pilot.common.dto.DatasetMQTTPublishDTO;
import openwis.pilot.rdsh.server.common.dto.ChannelDTO;
import openwis.pilot.rdsh.server.common.dto.LDSHDTO;
import openwis.pilot.rdsh.server.manager.service.ChannelService;
import openwis.pilot.rdsh.server.manager.service.LDSHService;
import openwis.pilot.rdsh.server.manager.service.MQTTService;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;

import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
@OsgiServiceProvider(classes = { MQTTService.class })
public class MqttServiceImpl implements MQTTService {
	// Logger reference.
	private static final Logger LOGGER = Logger.getLogger(MqttServiceImpl.class
			.getName());

	// JSON mapper.
	private ObjectMapper mapper = new ObjectMapper()
			.setSerializationInclusion(Include.NON_NULL);

	// A reference to the Camel route used for publishing.
	@EndpointInject(uri = "direct:publish")
	ProducerTemplate publishRoute;

	@Inject
	ChannelService channelService;

	@Inject
	LDSHService lDSHService;

	// The name of the Camel header used to specify the topic on which
	// publishing takes place.
	private static final String CAMEL_MQTT_TOPIC_HEADER_NAME = "CamelMQTTPublishTopic";

	@Override
	public void publish(DatasetMQTTPublishDTO datasetMQTTPublishDTO) {
		try {
			// Keep a reference to the topic on which message is published.
			String topic = datasetMQTTPublishDTO.getTopic();
			monitorTopic(datasetMQTTPublishDTO);

			// Redact info before publishing.
			datasetMQTTPublishDTO.setToken(null);
			datasetMQTTPublishDTO.setTopic(null);
			// Publish.
			publishRoute.sendBodyAndHeader(mapper.writeValueAsString(datasetMQTTPublishDTO),CAMEL_MQTT_TOPIC_HEADER_NAME, topic);
			
		} catch (JsonProcessingException e) {
			LOGGER.log(Level.SEVERE, "Could not publish to topic.", e);
			recordFailure(datasetMQTTPublishDTO);
		}
	}

	private void monitorTopic(DatasetMQTTPublishDTO datasetMQTTPublishDTO) {

		ChannelDTO channelDTO = channelService.getChannel(datasetMQTTPublishDTO.getDatasetName(), datasetMQTTPublishDTO.getDownloadURL());

		try {
			if (channelDTO == null) {
				channelDTO = new ChannelDTO();
				channelDTO.setChannelName(datasetMQTTPublishDTO.getDatasetName());
				channelDTO.setChannelUri(datasetMQTTPublishDTO.getDownloadURL());
				channelDTO.setBytessSent(datasetMQTTPublishDTO.getBinaryContentBase64().getBytes("UTF-8").length);
				channelDTO.setMsessagesSent(1);
			} else {
				channelDTO.setBytessSent(channelDTO.getBytessSent()+ datasetMQTTPublishDTO.getBinaryContentBase64().getBytes("UTF-8").length);
				channelDTO.setMsessagesSent(channelDTO.getMsessagesSent() + 1);
			}
			LDSHDTO  ldsh = lDSHService.getLDSHbyToken(datasetMQTTPublishDTO.getToken());
			System.out.println(ldsh.getSystemId());
			channelDTO.setLdshDto(lDSHService.getLDSHbyToken(datasetMQTTPublishDTO.getToken()));
			channelService.saveChannel(channelDTO);

		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		}
	}

	private void recordFailure(DatasetMQTTPublishDTO datasetMQTTPublishDTO) {
		ChannelDTO channelDTO = channelService.getChannel(
				datasetMQTTPublishDTO.getDatasetName(),
				datasetMQTTPublishDTO.getDownloadURL());
		if (channelDTO != null) {
			channelDTO.setFailedConnections(channelDTO.getFailedConnections() + 1);
			channelService.saveChannel(channelDTO);
		}
	}

}
