package openwis.pilot.awisc.server.manager.util;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.eurodyn.qlack2.util.jwt.JWTUtil;
import com.eurodyn.qlack2.util.jwt.api.JWTGenerateRequest;

import openwis.pilot.awisc.server.common.util.Constants;

public class JwtUtil {

	private static final Map<String, Long> tokenMap = new HashMap<String, Long>();

	private static final Logger logger = Logger.getLogger(JwtUtil.class.getName());

	// Sample method to construct a JWT
	public static String createJWT(String username, String secret) {

		String token = null;

		JWTGenerateRequest request = new JWTGenerateRequest();
		request.setId(Constants.JWT_ISSUER);
		request.setTtl(Constants.JWT_TTL);

		request.setSecret(secret);

		request.setSubject(username);
		request.setIssuer(Constants.JWT_ISSUER);
		token = JWTUtil.generateToken(request);

		return token;
	}

	public static boolean istokenValid(String token) {
		return tokenMap.get(token) != null;
	}

	public static Long getUserId(String token) {
		return tokenMap.get(token);
	}

	public static boolean deleteToken(String token) {
		return tokenMap.remove(token) != null;
	}
}
