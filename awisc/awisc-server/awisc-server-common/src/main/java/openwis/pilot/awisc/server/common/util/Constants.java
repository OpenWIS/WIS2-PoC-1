package openwis.pilot.awisc.server.common.util;

public class Constants {
	
	 // The issuer name of JWT.
	  public static final String JWT_ISSUER = "awisc";

	  // The TTL for the JWT.
	  public static final long JWT_TTL = 1000l * 60l * 60l * 8l;

	  // The name of the settings key holding the secret for the JWT.
	  public static final String JWT_SECRET_SETTING_KEY = "jwt_secret";

	public static enum MessageType {

		INFORMATION, ERROR

	}


	public static enum MessageCode {

		SUCCESS,
		LOGIN_SUCCESS, 
		LOGOUT_SUCCESS,
		SETTINGS_SAVE_SUCCESS,
		LDSH_DELETE_SUCCESS,
		LDSH_SAVE_SUCCESS,
		LDSH_INDEXING_SUCCESS
		

	}

	public static enum ErrorCode {

		UNEXPECTED_ERROR, 
		UNAUTHORIZED, 
		FORBIDDEN, 
		LOGIN_ERROR, 
		LOGOUT_ERROR, 
		SETTINGS_SAVE_ERROR,
		LDSH_DELETE_ERROR,
		LDSH_GET_ERROR,
		LDSH_SAVE_ERROR,
		LDSH_SAVE_ERROR_DUPLICATE_SYSTEM_ID

	}

}
