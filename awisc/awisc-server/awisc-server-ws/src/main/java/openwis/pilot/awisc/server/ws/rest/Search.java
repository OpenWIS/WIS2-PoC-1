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

import org.ops4j.pax.cdi.api.OsgiService;

import openwis.pilot.awisc.server.common.dto.SearchDTO;
import openwis.pilot.awisc.server.common.dto.SearchResultsDTO;
import openwis.pilot.awisc.server.common.dto.ServiceMessage;
import openwis.pilot.awisc.server.common.exception.ServiceException;
import openwis.pilot.awisc.server.common.util.Constants;
import openwis.pilot.awisc.server.common.util.Constants.ErrorCode;
import openwis.pilot.awisc.server.manager.service.SearchService;
import openwis.pilot.awisc.server.manager.util.JwtUtil;
import openwis.pilot.awisc.server.ws.util.HasHttpHeaders;
import openwis.pilot.awisc.server.ws.util.WebUtil;

@Singleton
@Path("/search")
public class Search implements HasHttpHeaders {

	private static final Logger logger = Logger.getLogger(Search.class.getName());

	@Context
	private HttpHeaders httpHeaders;

	@Inject
	@OsgiService
	private SearchService searchService;

	@POST
	@Path("/simple")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public SearchResultsDTO simpleSearch(SearchDTO searchDTO) throws ServiceException {
		try {
			SearchResultsDTO results = searchService.simpleSearch(searchDTO.getFreeText());
			return results;
		}
		catch(Throwable t) {
			ServiceException.rethrow(t);
		}
		return null;				
	}

	@POST
	@Path("/logout")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ServiceMessage logout(String logoutMessage) throws ServiceException {

		String token = WebUtil.getAuthorizationToken(httpHeaders);
		boolean deleted = JwtUtil.deleteToken(token);

		if (deleted) {
			return new ServiceMessage(Constants.MessageCode.LOGOUT_SUCCESS);
		}

		throw new ServiceException(ErrorCode.LOGOUT_ERROR);

	}

	@Override
	public HttpHeaders getHttpHeaders() {
		return httpHeaders;
	}

}
