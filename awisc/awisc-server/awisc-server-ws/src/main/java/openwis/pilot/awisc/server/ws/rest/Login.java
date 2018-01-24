package openwis.pilot.awisc.server.ws.rest;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ops4j.pax.cdi.api.OsgiService;

import openwis.pilot.awisc.server.common.annotation.RestServiceWrapper;
import openwis.pilot.awisc.server.common.annotation.ServiceExceptionWrapper;
import openwis.pilot.awisc.server.common.dto.ServiceMessage;
import openwis.pilot.awisc.server.common.dto.UserDTO;
import openwis.pilot.awisc.server.common.exception.ServiceException;
import openwis.pilot.awisc.server.common.util.Constants;
import openwis.pilot.awisc.server.common.util.Constants.ErrorCode;
import openwis.pilot.awisc.server.common.util.Constants.MessageCode;
import openwis.pilot.awisc.server.manager.service.LoginService;
import openwis.pilot.awisc.server.manager.util.JwtUtil;
import openwis.pilot.awisc.server.ws.util.HasHttpHeaders;
import openwis.pilot.awisc.server.ws.util.WebConstants;
import openwis.pilot.awisc.server.ws.util.WebUtil;

@Singleton
public class Login implements HasHttpHeaders {

	private static final Logger logger = Logger.getLogger(Login.class.getName());

	@Context
	private HttpHeaders httpHeaders;

	@Inject
	@OsgiService
	private LoginService loginService;

	@ServiceExceptionWrapper
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(UserDTO user) throws ServiceException {
		UserDTO fromDb = loginService.login(user);
		return WebUtil.getResponseBuilder(new ServiceMessage(MessageCode.LOGIN_SUCCESS))
				.header(WebConstants.Headers.X_AUTHORIZATION, JwtUtil.createJWT(fromDb.getId())).build();
				
	}

	@RestServiceWrapper
	@POST
	@Path("/logout")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ServiceMessage logout(String logoutMessage) throws ServiceException {

		String token = WebUtil.getXAuthorizationToken(httpHeaders);
		boolean deleted = JwtUtil.deleteToken(token);

		if (deleted) {
			return new ServiceMessage(Constants.MessageCode.LOGOUT_SUCCESS);
		}

		throw new ServiceException(ErrorCode.LOGOUT_FAILURE);

	}

	@Override
	public HttpHeaders getHttpHeaders() {
		return httpHeaders;
	}

}
