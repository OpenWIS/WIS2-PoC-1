package openwis.pilot.awisc.server.manager.impl;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.ops4j.pax.cdi.api.OsgiService;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;

import openwis.pilot.awisc.server.common.dto.ServerMessage;
import openwis.pilot.awisc.server.common.util.Constants;
import openwis.pilot.awisc.server.db.dao.GenericDao;
import openwis.pilot.awisc.server.db.dao.IDaoFactory;
import openwis.pilot.awisc.server.db.dao.simplequery.Query;
import openwis.pilot.awisc.server.db.dao.simplequery.QueryFragment;
import openwis.pilot.awisc.server.db.model.User;
import openwis.pilot.awisc.server.manager.helper.LoginHelper;
import openwis.pilot.awisc.server.manager.service.LoginService;

@Singleton
@OsgiServiceProvider(classes = { LoginService.class })
public class LoginServiceImpl implements LoginService {

	@Inject
	@OsgiService
	private IDaoFactory iDaoFactory;

	private static final Logger logger = Logger.getLogger(LoginHelper.class.getName());
	

	public ServerMessage login(openwis.pilot.awisc.server.common.dto.User user) {
		GenericDao<User> userDao = iDaoFactory.getDao(User.class);
				
		QueryFragment qf = Query.and(Query.eq("username", user.getUsername()),
				Query.eq("password", user.getPassword()));
		User u = userDao.executeSimpleGetQuery(qf);
		
		ServerMessage response = new ServerMessage();
		
		if(u!=null) {
			response.setType(Constants.Messages.Types.MESSAGE);
			response.setCode(Constants.Messages.Codes.LOGIN_SUCCESS);
		}
		else {
			response.setType(Constants.Messages.Types.ERROR);
			response.setCode(Constants.Messages.Codes.LOGIN_FAILURE);
		}
		
		return response;

	}

}
