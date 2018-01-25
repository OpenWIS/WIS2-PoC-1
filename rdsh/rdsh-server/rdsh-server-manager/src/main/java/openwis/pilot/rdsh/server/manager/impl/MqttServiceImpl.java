package openwis.pilot.rdsh.server.manager.impl;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.inject.Singleton;
import openwis.pilot.common.dto.DatasetMQTTPublishDTO;
import openwis.pilot.rdsh.server.manager.service.MQTTService;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;

import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
@OsgiServiceProvider(classes = {MQTTService.class})
public class MqttServiceImpl implements MQTTService {
  // Logger reference.
  private static final Logger LOGGER = Logger.getLogger(SettingsServiceImpl.class.getName());

  // JSON mapper.
  private ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(Include.NON_NULL);

  // A reference to the Camel route used for publishing.
  @EndpointInject(uri = "direct:publish")
  ProducerTemplate publishRoute;

  // The name of the Camel header used to specify the topic on which publishing takes place.
  private static final String CAMEL_MQTT_TOPIC_HEADER_NAME = "CamelMQTTPublishTopic";

  @Override
  public void publish(DatasetMQTTPublishDTO datasetMQTTPublishDTO) {
    try {
      // Keep a reference to the topic on which message is published.
      String topic = datasetMQTTPublishDTO.getTopic();

      // Redact info before publishing.
      datasetMQTTPublishDTO.setToken(null);
      datasetMQTTPublishDTO.setTopic(null);

      // Publish.
      publishRoute.sendBodyAndHeader(mapper.writeValueAsString(datasetMQTTPublishDTO), CAMEL_MQTT_TOPIC_HEADER_NAME, topic);
    } catch (JsonProcessingException e) {
      LOGGER.log(Level.SEVERE, "Could not publish to topic.", e);
    }
  }
}
