package openwis.pilot.awisc.server.ws.rest;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.apache.cxf.rs.security.cors.LocalPreflight;
import org.ops4j.pax.cdi.api.OsgiService;

import openwis.pilot.awisc.server.common.annotation.RestServiceWrapper;
import openwis.pilot.awisc.server.common.annotation.ServiceExceptionWrapper;
import openwis.pilot.awisc.server.common.dto.ServiceMessage;
import openwis.pilot.awisc.server.common.exception.ServiceException;
import openwis.pilot.awisc.server.common.util.Constants;
import openwis.pilot.awisc.server.manager.service.LoginService;
import openwis.pilot.awisc.server.manager.util.JwtUtil;
import openwis.pilot.awisc.server.ws.util.HasHttpHeaders;
import openwis.pilot.awisc.server.ws.util.WebUtil;

@CrossOriginResourceSharing(allowOrigins = { "*" }, allowCredentials = true
// maxAge = 1,
// allowHeaders = {
// "X-custom-1", "X-custom-2"
// },
// exposeHeaders = {
// "X-custom-3", "X-custom-4"
// }
)
@Singleton
public class Login implements HasHttpHeaders{

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
	public Response login(openwis.pilot.awisc.server.common.dto.User user) throws ServiceException{
		
		try {
			ServiceMessage sm = loginService.login(user);
			return WebUtil.getResponseBuilder(sm).header("X-Authorization", JwtUtil.createJWT(user.getId())).build();
			
		}
		catch(Throwable t) {
			ServiceException.rethrow(t);
		}
		return null;
		
	}

	@RestServiceWrapper
	@POST
	@Path("/logout")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ServiceMessage logout(String logoutMessage) throws ServiceException{
		
		String token = WebUtil.getXAuthorizationToken(httpHeaders);
		boolean deleted = JwtUtil.deleteToken(token);
		
		if(deleted) {
			return new ServiceMessage().setType(Constants.MessageType.INFORMATION)
					.setCode(Constants.ResponseCode.LOGOUT_SUCCESS);
		}
		
		return new ServiceMessage().setType(Constants.MessageType.ERROR)
				.setCode(Constants.ErrorCode.LOGOUT_FAILURE);	

	}

	@RestServiceWrapper
	@OPTIONS
	@Path("{path : .*}")
	@LocalPreflight
	public void options() {
		
	}

	@Override
	public HttpHeaders getHttpHeaders() {
		return httpHeaders;
	}

}
