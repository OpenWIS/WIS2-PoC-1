package openwis.pilot.awisc.server.manager.impl;

import java.util.logging.Logger;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;

import com.querydsl.jpa.impl.JPAQueryFactory;

import openwis.pilot.awisc.server.common.dto.UserDTO;
import openwis.pilot.awisc.server.common.exception.ServiceException;
import openwis.pilot.awisc.server.common.util.Constants;
import openwis.pilot.awisc.server.manager.mappers.UserMapperImpl;
import openwis.pilot.awisc.server.manager.model.QUser;
import openwis.pilot.awisc.server.manager.model.User;
import openwis.pilot.awisc.server.manager.service.LoginService;

@Singleton
@OsgiServiceProvider(classes = { LoginService.class })
public class LoginServiceImpl implements LoginService {
	private static final Logger logger = Logger.getLogger(LoginServiceImpl.class.getName());
	private static QUser qUser = QUser.user;

	@PersistenceContext(unitName = "awisc-pu")
	private EntityManager em;

	public UserDTO login(UserDTO userDTO) throws ServiceException {
		final User user = new JPAQueryFactory(em).selectFrom(qUser)
				.where(qUser.username.eq(userDTO.getUsername()).and(qUser.password.eq(userDTO.getPassword())))
				.fetchOne();

		if (user == null) {
			throw new ServiceException(Constants.ErrorCode.LOGIN_ERROR);
		}

		return new UserMapperImpl().toUserDTO(user);
	}

}
