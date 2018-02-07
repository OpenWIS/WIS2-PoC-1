package openwis.pilot.ldsh.common.util;

public class Constants {

	  // The issuer name of JWT.
	  public static final String JWT_ISSUER = "ldsh";

	  // The TTL for the JWT.
	  public static final long JWT_TTL = 1000l * 60l * 60l * 8l;

	  // The name of the settings key holding the secret for the JWT.
	  public static final String JWT_SECRET_SETTING_KEY = "jwt_secret";
	
	
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
