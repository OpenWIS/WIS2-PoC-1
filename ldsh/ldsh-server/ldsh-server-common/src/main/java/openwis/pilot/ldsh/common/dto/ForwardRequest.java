package openwis.pilot.ldsh.common.dto;

import java.io.Serializable;

public class ForwardRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2811408122943041041L;

	private String method;
	private String uri;
	private String data;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
