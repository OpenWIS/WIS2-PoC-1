package openwis.pilot.rdsh.server.manager.impl;

import com.eurodyn.qlack2.fuse.crypto.api.CryptoService;
import com.eurodyn.qlack2.util.jwt.JWTUtil;
import com.eurodyn.qlack2.util.jwt.api.JWTGenerateRequest;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import openwis.pilot.common.exception.AuthenticationException;
import openwis.pilot.rdsh.server.common.dto.SettingDTO;
import openwis.pilot.rdsh.server.common.util.Constants;
import openwis.pilot.rdsh.server.manager.model.QUser;
import openwis.pilot.rdsh.server.manager.model.User;
import openwis.pilot.rdsh.server.manager.service.JWTService;
import openwis.pilot.rdsh.server.manager.service.SettingsService;
import org.ops4j.pax.cdi.api.OsgiService;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
@OsgiServiceProvider(classes = {JWTService.class})
public class JWTServiceImpl implements JWTService {

  // Logger reference.
  private static final Logger LOGGER = Logger.getLogger(LDSHServiceImpl.class.getName());

  // Entity Manager ref.
  @PersistenceContext(unitName = "rdsh-pu")
  private EntityManager em;

  // Reference to crypto utility service.
  @Inject
  @OsgiService
  private CryptoService cryptoService;

  @Inject
  private SettingsService settingsService;

  // QueryDSL helpers.
  private static QUser qUser = QUser.user;

  @Override
  public String authenticate(String username, String password) {
    // The JWT to return if authentication was successful.
    String jwt = null;

    // Find the user by its username.
    final User user = new JPAQueryFactory(em)
        .selectFrom(qUser)
        .where(qUser.username.eq(username))
        .fetchOne();

    // If a user was found, check the hashed version of the password and produce a JWT if a match
    // is found.
    if (user != null) {
      try {
        String hashedPassword = cryptoService.md5(user.getSalt() + password).toLowerCase();
        if (hashedPassword.equals(user.getPassword().toLowerCase())) {
          JWTGenerateRequest request = new JWTGenerateRequest();
          request.setId(Constants.JWT_ISSUER);
          request.setTtl(Constants.JWT_TTL);
          final Optional<SettingDTO> setting = settingsService
              .getSetting(Constants.JWT_SECRET_SETTING_KEY);
          if (setting.isPresent()) {
            request.setSecret(setting.get().getSettingVal());
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
    }

    return jwt;
  }
}
