package openwis.pilot.common.dto;

/**
 * A placeholder to publish MQTT messages from LDSH to RDSH.
 */
public class DatasetMQTTPublishDTO {
  // The security token assigned to the LDSH by RDSH.
  private String token;

  // The topic on which publishing takes place.
  private String topic;
  
  // The message to publish.
  private String message;

  // The message's Dataset name.
  private String datasetName;

  // The URL from which data can be download on the LDSH.
  private String downloadURL;

  // Binary content in Base64 encoding.
  private String binaryContentBase64;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  public String getDatasetName() {
    return datasetName;
  }

  public void setDatasetName(String datasetName) {
    this.datasetName = datasetName;
  }


  public String getDownloadURL() {
    return downloadURL;
  }

  public void setDownloadURL(String downloadURL) {
    this.downloadURL = downloadURL;
  }

  public String getBinaryContentBase64() {
    return binaryContentBase64;
  }

  public void setBinaryContentBase64(String binaryContentBase64) {
    this.binaryContentBase64 = binaryContentBase64;
  }
}
