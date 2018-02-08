package openwis.pilot.ldsh.manager.impl;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.eurodyn.qlack2.util.jwt.JWTUtil;
import com.eurodyn.qlack2.fuse.crypto.api.CryptoService;
import com.eurodyn.qlack2.util.jwt.api.JWTGenerateRequest;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.ops4j.pax.cdi.api.OsgiService;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;

import openwis.pilot.common.exception.AuthenticationException;
import openwis.pilot.ldsh.common.util.Constants;
import openwis.pilot.ldsh.manager.model.User;
import openwis.pilot.ldsh.manager.model.QUser;
import openwis.pilot.ldsh.manager.service.JWTService;
import openwis.pilot.ldsh.manager.service.SystemService;

@Singleton
@OsgiServiceProvider(classes = { JWTService.class })
public class JWTServiceImpl implements JWTService {

	// Logger reference.
	private static final Logger LOGGER = Logger.getLogger(JWTServiceImpl.class.getName());

	// Entity Manager ref.
	@PersistenceContext(unitName = "ldshPU")
	private EntityManager em;

	// Reference to crypto utility service.
	@Inject
	@OsgiService
	private CryptoService cryptoService;

	@Inject
	private SystemService settingsService;

	// QueryDSL helpers.
	private static QUser qUser = QUser.user;

	@Override
	public String authenticate(String username, String password) {
		// The JWT to return if authentication was successful.
		String jwt = null;

		// Find the user by its username.
		final User user = new JPAQueryFactory(em).selectFrom(qUser).where(qUser.username.eq(username)).fetchOne();

		// If a user was found, check the hashed version of the password and
		// produce a JWT if a match is found.
		if (user != null) {
			try {
				String hashedPassword = cryptoService.md5(
						user.getSalt() + password).toLowerCase();
				if (hashedPassword.equals(user.getPassword().toLowerCase())) {
					JWTGenerateRequest request = new JWTGenerateRequest();
					request.setId(Constants.JWT_ISSUER);
					request.setTtl(Constants.JWT_TTL);

					String settingVal = settingsService.getSystemConfigurationValue(Constants.JWT_SECRET_SETTING_KEY);

					if (!settingVal.isEmpty()) {
						request.setSecret(settingVal);
					}
					request.setSubject(username);
					request.setIssuer(Constants.JWT_ISSUER);
					jwt = JWTUtil.generateToken(request);
				} else {
					throw new AuthenticationException();
				}
			} catch (NoSuchAlgorithmException e) {
				LOGGER.log(Level.SEVERE, "Could not check password.", e);
				throw new AuthenticationException();
			}
		} else { // Username not found. 
			throw new AuthenticationException();
		}

		return jwt;
	}
}
