package openwis.pilot.rdsh.server.manager.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rdsh_channel")
public class Channel implements Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column
	private Long id;

	@Column(name = "channel_name")
	private String channelName;

	@Column(name = "channel_uri")
	private String channelUri;

	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "system_id")
	private Ldsh ldsh;

	@Column(name = "msessages_sent")
	private long msessagesSent;

	@Column(name = "bytes_sent")
	private long bytessSent;

	@Column(name = "failed_connections")
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

	public Ldsh getLdsh() {
		return ldsh;
	}

	public void setLdsh(Ldsh ldsh) {
		this.ldsh = ldsh;
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
