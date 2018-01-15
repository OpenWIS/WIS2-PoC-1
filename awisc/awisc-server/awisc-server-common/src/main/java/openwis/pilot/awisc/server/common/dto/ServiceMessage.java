package openwis.pilot.awisc.server.common.dto;

import openwis.pilot.awisc.server.common.util.Constants.MessageCode;
import openwis.pilot.awisc.server.common.util.Constants.MessageType;

import java.io.Serializable;

public class ServiceMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8429010260399049889L;

	private MessageCode code;
	private MessageType type;

	public MessageCode getCode() {
		return code;
	}

	public ServiceMessage setCode(MessageCode code) {
		this.code = code;
		return this;
	}

	public MessageType getType() {
		return type;
	}

	public ServiceMessage setType(MessageType type) {
		this.type = type;
		return this;
	}

}
