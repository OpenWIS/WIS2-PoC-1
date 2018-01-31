package openwis.pilot.awisc.server.common.util;

public class Constants {

	public static enum MessageType {

		INFORMATION, ERROR

	}


	public static enum MessageCode {

		SUCCESS,
		LOGIN_SUCCESS, 
		LOGOUT_SUCCESS,
		SETTINGS_SAVE_SUCCESS
		

	}

	public static enum ErrorCode {

		UNEXPECTED_ERROR, UNAUTHORIZED, FORBIDDEN, LOGIN_ERROR, LOGOUT_ERROR, SETTINGS_SAVE_ERROR

	}

}
