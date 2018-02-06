package openwis.pilot.awisc.server.ws.util;

import java.util.List;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.rs.security.cors.CorsHeaderConstants;

import openwis.pilot.awisc.server.common.dto.ServiceError;
import openwis.pilot.awisc.server.common.dto.ServiceMessage;
import openwis.pilot.awisc.server.common.exception.ServiceException;
import openwis.pilot.awisc.server.common.util.Constants.ErrorCode;

public class WebUtil {

	public static String getAuthorizationToken(HttpHeaders httpHeaders) throws ServiceException {
		List<String> header = httpHeaders.getRequestHeader(WebConstants.Headers.AUTHORIZATION);
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
		Status status = Status.ACCEPTED;
		ResponseBuilder builder = Response.ok(sm);
		return builder.status(status);
		//return appendCorsHeaders(builder.status(status));
	}
	
	public static Response buildResponse(ServiceError se) {
		return getResponseBuilder(se).build();
	}
	
	public static ResponseBuilder getResponseBuilder(ServiceError se) {
		Status status = null;
		ResponseBuilder builder = Response.serverError();
		if(ErrorCode.FORBIDDEN.equals(se.getCode())) {
			status = Status.FORBIDDEN;
		}
		else if(ErrorCode.UNAUTHORIZED.equals(se.getCode())) {
			status = Status.UNAUTHORIZED;
		}
		else {
			status = Status.INTERNAL_SERVER_ERROR;
		}
		return appendCorsHeaders(Response.status(status).entity(se));
	}

}
