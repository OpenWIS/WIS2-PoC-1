package openwis.pilot.rdsh.server.manager.service;

import openwis.pilot.common.dto.DatasetMQTTPublishDTO;

public interface MQTTService {

  /**
   * Publishes a message to an MQTT topic. Note that it is the responsibility of the caller of this
   * method to guarantee that posting is allowed and that all related security checks have been
   * performed.
   * @param datasetMQTTPublishDTO The details of the message to post.
   */
  void publish(DatasetMQTTPublishDTO datasetMQTTPublishDTO);
}
