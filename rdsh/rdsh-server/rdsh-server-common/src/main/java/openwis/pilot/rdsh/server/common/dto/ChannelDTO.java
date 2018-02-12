package openwis.pilot.rdsh.server.common.dto;


public class ChannelDTO {

	private Long id;
	private String channelName;
	private String channelUri;
	private LDSHDTO ldshDto;
	private long msessagesSent;
	private long bytessSent;
	private long failedConnections;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getChannelUri() {
		return channelUri;
	}
	public void setChannelUri(String channelUri) {
		this.channelUri = channelUri;
	}
	public LDSHDTO getLdshDto() {
		return ldshDto;
	}
	public void setLdshDto(LDSHDTO ldshDto) {
		this.ldshDto = ldshDto;
	}
	public long getMsessagesSent() {
		return msessagesSent;
	}
	public void setMsessagesSent(long msessagesSent) {
		this.msessagesSent = msessagesSent;
	}
	public long getBytessSent() {
		return bytessSent;
	}
	public void setBytessSent(long bytessSent) {
		this.bytessSent = bytessSent;
	}
	public long getFailedConnections() {
		return failedConnections;
	}
	public void setFailedConnections(long failedConnections) {
		this.failedConnections = failedConnections;
	}
	
	
	
}
