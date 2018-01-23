package openwis.pilot.rdsh.server.common.util;

public class Constants {

	public static enum MessageType {

		INFORMATION, ERROR

	}

	public static interface MessageCode {

	}

	public static enum ResponseCode implements MessageCode {

		LOGIN_SUCCESS, LOGOUT_SUCCESS

	}

	public static enum ErrorCode implements MessageCode {

		UNEXPECTED_ERROR, UNAUTHORIZED, FORBIDDEN, LOGIN_FAILURE, LOGOUT_FAILURE

	}

}
