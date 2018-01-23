package openwis.pilot.rdsh.server.manager.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JwtUtil {

	private static final Map<String, Long> tokenMap = new HashMap<String, Long>();

	private static final Logger logger = Logger.getLogger(JwtUtil.class.getName());

	// Sample method to construct a JWT
	public static String createJWT(long userId) {
		
		String token = null;
		try {
			token = "Bearer " + Jwts.builder().setSubject(UUID.randomUUID().toString())
					.signWith(SignatureAlgorithm.HS256, "secret".getBytes("UTF-8")).compact();

			tokenMap.put(token, userId);
			return token;
		} catch (UnsupportedEncodingException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
		return null;
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
