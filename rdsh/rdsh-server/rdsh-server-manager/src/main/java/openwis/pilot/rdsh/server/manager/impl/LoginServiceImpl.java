package openwis.pilot.rdsh.server.manager.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import openwis.pilot.rdsh.server.common.dto.UserDTO;
import openwis.pilot.rdsh.server.manager.mappers.UserMapperImpl;
import openwis.pilot.rdsh.server.manager.model.QUser;
import openwis.pilot.rdsh.server.manager.model.User;
import openwis.pilot.rdsh.server.manager.service.LoginService;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;

import java.util.logging.Logger;

@Singleton
@OsgiServiceProvider(classes = {LoginService.class})
public class LoginServiceImpl implements LoginService {
  private static final Logger logger = Logger.getLogger(LoginServiceImpl.class.getName());
  private static QUser qUser = QUser.user;

  @PersistenceContext(unitName = "rdsh-pu")
  private EntityManager em;

  public UserDTO login(UserDTO userDTO) {
    final User user = new JPAQueryFactory(em)
        .selectFrom(qUser)
        .where(qUser.username.eq(userDTO.getUsername())
            .and(qUser.password.eq(userDTO.getPassword())))
        .fetchOne();

    return new UserMapperImpl().toUserDTO(user);
  }
}
