package openwis.pilot.awisc.server.common.util;

public class Constants {

	public static enum MessageType {

		INFORMATION, ERROR

	}


	public static enum MessageCode {

		LOGIN_SUCCESS, LOGOUT_SUCCESS

	}

	public static enum ErrorCode {

		UNEXPECTED_ERROR, UNAUTHORIZED, FORBIDDEN, LOGIN_FAILURE, LOGOUT_FAILURE

	}

}
