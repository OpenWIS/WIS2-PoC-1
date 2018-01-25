package openwis.pilot.awisc.server.common.dto;

import java.io.Serializable;

import openwis.pilot.awisc.server.common.util.Constants.MessageCode;

public class ServiceMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8429010260399049889L;

	private MessageCode code;
	
	public ServiceMessage() {}
	
	public ServiceMessage(MessageCode code) {
		this.code = code;
	}

	public MessageCode getCode() {
		return code;
	}

	public ServiceMessage setCode(MessageCode code) {
		this.code = code;
		return this;
	}

}
