package openwis.pilot.awisc.server.common.dto;

import java.io.Serializable;

public class ServerMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8429010260399049889L;

	private String code;
	private String type;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
