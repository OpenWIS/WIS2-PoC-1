package openwis.pilot.rdsh.server.ws.util;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import openwis.pilot.rdsh.server.common.dto.ServiceMessage;
import openwis.pilot.rdsh.server.common.exception.ServiceException;
import openwis.pilot.rdsh.server.common.util.Constants;
import openwis.pilot.rdsh.server.common.util.Constants.ErrorCode;
import openwis.pilot.rdsh.server.common.util.Constants.MessageType;
import org.apache.cxf.rs.security.cors.CorsHeaderConstants;

import java.util.List;

public class WebUtil {

	public static String getXAuthorizationToken(HttpHeaders httpHeaders) throws ServiceException {
		List<String> header = httpHeaders.getRequestHeader(WebConstants.Headers.X_AUTHORIZATION);
		if (header.size() == 0) {
			throw new ServiceException(ErrorCode.UNAUTHORIZED);
		}
		return header.get(0);
	}
	
	public static ResponseBuilder appendCorsHeaders(ResponseBuilder builder) {
		return builder.header(CorsHeaderConstants.HEADER_AC_ALLOW_METHODS, "DELETE PUT POST")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_CREDENTIALS, "true")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_ORIGIN, "*")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_HEADERS,
						"origin, content-type, accept, Origin, Content-type, Accept, X-Authorization");
	}

	public static Response buildResponse(Object o) {
		return getResponseBuilder(o).build();
	}
	
	public static ResponseBuilder getResponseBuilder(Object o) {
		return appendCorsHeaders(Response.ok().status(Status.ACCEPTED));
	}
	
	public static Response buildResponse(ServiceMessage sm) {
		return getResponseBuilder(sm).build();
	}
	
	public static ResponseBuilder getResponseBuilder(ServiceMessage sm) {
		Status status = null;
		ResponseBuilder builder = null;
		if(MessageType.INFORMATION.equals(sm.getType())) {
			status = Status.ACCEPTED;
			builder = Response.ok(sm);
		}
		else if(ErrorCode.FORBIDDEN.equals(sm.getCode())) {
			status = Status.FORBIDDEN;
			builder = Response.serverError();
		}
		else if(ErrorCode.UNAUTHORIZED.equals(sm.getCode())) {
			status = Status.UNAUTHORIZED;
			builder = Response.serverError();
		}
		else {
			status = Status.ACCEPTED;
			builder = Response.ok(sm);
		}
		
		return appendCorsHeaders(builder.status(status));
	}

	ServiceMessage sm = new ServiceMessage().setType(Constants.MessageType.ERROR)
			.setCode(Constants.ErrorCode.UNAUTHORIZED);

}
