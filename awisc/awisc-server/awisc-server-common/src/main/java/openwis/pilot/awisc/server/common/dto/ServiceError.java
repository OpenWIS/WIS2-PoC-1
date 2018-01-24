package openwis.pilot.awisc.server.common.dto;

import java.io.Serializable;

import openwis.pilot.awisc.server.common.util.Constants.ErrorCode;

public class ServiceError implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8429010260399049889L;

	private ErrorCode code;
	
	public ServiceError() {}
	
	public ServiceError(ErrorCode code) {
		this.code = code;
	}

	public ErrorCode getCode() {
		return code;
	}

	public ServiceError setCode(ErrorCode code) {
		this.code = code;
		return this;
	}

}
