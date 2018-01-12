package openwis.pilot.awisc.server.manager.impl;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.ops4j.pax.cdi.api.OsgiService;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;

import openwis.pilot.awisc.server.common.dto.ServiceMessage;
import openwis.pilot.awisc.server.common.util.Constants.ErrorCode;
import openwis.pilot.awisc.server.common.util.Constants.MessageType;
import openwis.pilot.awisc.server.common.util.Constants.ResponseCode;
import openwis.pilot.awisc.server.db.dao.GenericDao;
import openwis.pilot.awisc.server.db.dao.IDaoFactory;
import openwis.pilot.awisc.server.db.dao.simplequery.Query;
import openwis.pilot.awisc.server.db.dao.simplequery.QueryFragment;
import openwis.pilot.awisc.server.db.model.User;
import openwis.pilot.awisc.server.manager.service.LoginService;

@Singleton
@OsgiServiceProvider(classes = { LoginService.class })
public class LoginServiceImpl implements LoginService {

	@Inject
	@OsgiService
	private IDaoFactory iDaoFactory;

	private static final Logger logger = Logger.getLogger(LoginServiceImpl.class.getName());
	

	public ServiceMessage login(openwis.pilot.awisc.server.common.dto.User user) {
		GenericDao<User> userDao = iDaoFactory.getDao(User.class);
				
		QueryFragment qf = Query.and(Query.eq("username", user.getUsername()),
				Query.eq("password", user.getPassword()));
		User u = userDao.executeSimpleGetQuery(qf);
		
		ServiceMessage response = new ServiceMessage();
		
		if(u!=null) {
			response.setType(MessageType.INFORMATION);
			response.setCode(ResponseCode.LOGIN_SUCCESS);
		}
		else {
			response.setType(MessageType.ERROR);
			response.setCode(ErrorCode.LOGIN_FAILURE);
		}
		
		return response;

	}

}
