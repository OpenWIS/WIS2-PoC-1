package openwis.pilot.awisc.server.manager.helper;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class LoginHelper {

	private static final Map<Long, String> loginMap = new HashMap<Long, String>();

	private static long key;
	private static final Logger logger = Logger.getLogger(LoginHelper.class.getName());

	// Sample method to construct a JWT
	public static void createJWT(long userId) {

		try {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String jwt = "Bearer " + Jwts.builder().setSubject(UUID.randomUUID().toString())
					.signWith(SignatureAlgorithm.HS256, "secret".getBytes("UTF-8")).compact();

			loginMap.put(userId, jwt);
			key = userId;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			String jwt = "empty";
		}
	}

	public static String getToken(String token) {
		logger.log(Level.INFO, "The value from the token is-------------------------------------------> "
				+ loginMap.get(key) + "  The size is---->  " + loginMap.size());
		return loginMap.get(getUserId(token));
	}

	public static String generateToken() {
		logger.log(Level.INFO, "The KEY IS-------------------------------------------> " + key);
		return loginMap.get(key);
	}

	public static long getUserId(String token) {
		long idValue = -1;
		// for (Entry<Long, String> entry : loginMap.entrySet()) {
		for (Entry<Long, String> entry : loginMap.entrySet()) {
			if (entry.getValue().equals(token)) {
				return entry.getKey();
			}
		}

		return -1;
	}

	public static String deleteToken(String token) {

		logger.log(Level.INFO,
				"The value from the removed token is-------------------------------------------> " + loginMap.size());

		if (loginMap.size() > 0 || loginMap.size() == 0) {
			loginMap.remove(getUserId(token));
			return "Token was removed";
		} else {
			return "Something went wrong";
		}
	}
}
