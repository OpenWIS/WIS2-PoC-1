export interface MqttTopic {
  channelName: string,
  channelUri: string,
  id: string
  bytesSent: number,
  failedConnections: number,
  msessagesSent: number,
  topic: string
}