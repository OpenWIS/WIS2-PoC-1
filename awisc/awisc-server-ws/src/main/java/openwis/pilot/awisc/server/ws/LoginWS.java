package openwis.pilot.awisc.server.ws;

import java.util.logging.Level;
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

import org.apache.cxf.rs.security.cors.CorsHeaderConstants;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.apache.cxf.rs.security.cors.LocalPreflight;
import org.ops4j.pax.cdi.api.OsgiService;

import openwis.pilot.awisc.server.common.dto.ServerMessage;
import openwis.pilot.awisc.server.manager.helper.LoginHelper;
import openwis.pilot.awisc.server.manager.service.LoginService;

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
public class LoginWS {

	private static final Logger logger = Logger.getLogger(LoginWS.class.getName());

	@Context
	private HttpHeaders headers;

	@Inject
	@OsgiService
	private LoginService loginService;

	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(openwis.pilot.awisc.server.common.dto.User user) {

		logger.log(Level.INFO, user.toString());

		ServerMessage sm = loginService.login(user);

		logger.log(Level.INFO, sm.toString());

		return Response.ok(sm).header("Authorization", LoginHelper.generateToken())
				.header(CorsHeaderConstants.HEADER_AC_EXPOSE_HEADERS, "Authorization")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_METHODS, "DELETE PUT POST")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_CREDENTIALS, "true")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_ORIGIN, "*")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_HEADERS,
						"origin, content-type, accept, authorization , Origin, Content-type, Accept, Authorization")
				.build();
	}

	@POST
	@Path("/logout")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response logout(String logoutMessage) {

		if (LoginHelper.getToken(headers.getRequestHeader("Authorization").get(0))
				.equals(headers.getRequestHeader("Authorization").get(0)) && (logoutMessage.equals("removeToken"))) {
			// logger.log(Level.INFO, "DELETE
			// TOKEN-------------------------------------------> "+LoginHelper.getToken());

			return Response.ok(LoginHelper.deleteToken((headers.getRequestHeader("Authorization").get(0))))
					.header(CorsHeaderConstants.HEADER_AC_ALLOW_METHODS, "DELETE PUT POST")
					.header(CorsHeaderConstants.HEADER_AC_ALLOW_CREDENTIALS, "true")
					.header(CorsHeaderConstants.HEADER_AC_ALLOW_ORIGIN, "*")
					.header(CorsHeaderConstants.HEADER_AC_ALLOW_HEADERS,
							"origin, content-type, accept, Origin, Content-type, Accept, authorization, Authorization")
					.build();
		} else {
			return Response.ok("tokenNotRemoved").header(CorsHeaderConstants.HEADER_AC_ALLOW_METHODS, "DELETE PUT POST")
					.header(CorsHeaderConstants.HEADER_AC_ALLOW_CREDENTIALS, "true")
					.header(CorsHeaderConstants.HEADER_AC_ALLOW_ORIGIN, "*")
					.header(CorsHeaderConstants.HEADER_AC_ALLOW_HEADERS,
							"origin, content-type, accept, authorization , Origin, Content-type, Accept, Authorization")
					.build();
		}

	}

	@SuppressWarnings("unlikely-arg-type")
	@POST
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response testService(String user) {

		if (LoginHelper.getToken(headers.getRequestHeader("Authorization").get(0))
				.equals(headers.getRequestHeader("Authorization").get(0))) {
			return Response.ok("I am secure").header(CorsHeaderConstants.HEADER_AC_ALLOW_METHODS, "DELETE PUT POST")
					.header(CorsHeaderConstants.HEADER_AC_ALLOW_CREDENTIALS, "true")
					.header(CorsHeaderConstants.HEADER_AC_ALLOW_ORIGIN, "*")
					.header(CorsHeaderConstants.HEADER_AC_ALLOW_HEADERS,
							"origin, content-type, accept, Origin, Content-type, Accept, authorization, Authorization")
					.build();
		} else {
			return Response.ok("No Authorization")
					.header(CorsHeaderConstants.HEADER_AC_ALLOW_METHODS, "DELETE PUT POST")
					.header(CorsHeaderConstants.HEADER_AC_ALLOW_CREDENTIALS, "true")
					.header(CorsHeaderConstants.HEADER_AC_ALLOW_ORIGIN, "*")
					.header(CorsHeaderConstants.HEADER_AC_ALLOW_HEADERS,
							"origin, content-type, accept, authorization , Origin, Content-type, Accept, Authorization")
					.build();
		}

	}

	@OPTIONS
	@Path("{path : .*}")
	@LocalPreflight
	public Response options() {
		String origin = headers.getRequestHeader("Origin").get(0);

		return Response.ok().header(CorsHeaderConstants.HEADER_AC_ALLOW_METHODS, "DELETE PUT POST")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_CREDENTIALS, "true")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_ORIGIN, "*")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_HEADERS,
						"origin, content-type, accept, authorization, Origin, Content-type, Accept, Authorization")
				.build();
	}

}
