package openwis.pilot.awisc.server.ws.rest;

import java.io.UnsupportedEncodingException;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.ops4j.pax.cdi.api.OsgiService;

import openwis.pilot.awisc.server.common.dto.LdshDTO;
import openwis.pilot.awisc.server.common.dto.ServiceMessage;
import openwis.pilot.awisc.server.common.exception.ServiceException;
import openwis.pilot.awisc.server.common.util.Constants;
import openwis.pilot.awisc.server.manager.service.AwiscIndexingService;
import openwis.pilot.awisc.server.manager.service.LdshService;
import openwis.pilot.common.dto.awisc.LdshIndexDTO;
import openwis.pilot.common.security.JWTNeeded;

@Singleton
@Path("/ldsh")
public class Ldsh {

	@OsgiService
	@Inject
	private LdshService ldshService;
	
	@OsgiService
	@Inject
	private AwiscIndexingService awiscIndexingService;

	/**
	 * Get all Ldshs registered in the system.
	 *
	 * @return Returns a JSON representation of all Ldshs as
	 *         {@link openwis.pilot.rdsh.server.common.dto.LdshDTO}.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllLdsh() {
		return Response.ok(ldshService.getLdshs()).build();
	}

	/**
	 * Fetches a specific Ldsh by Id.
	 * 
	 * @param ldshId
	 *            The Id of the Ldsh to fetch.
	 * @return Returns a JSON representation of
	 *         {@link openwis.pilot.rdsh.server.common.dto.LdshDTO} or an empty
	 *         reply.
	 */
	@GET
	@JWTNeeded
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLdsh(@PathParam("id") Long ldshId) {
		return Response.ok(ldshService.getLdsh(ldshId)).build();
	}

	/**
	 * Saves or creates an Ldsh.
	 * 
	 * @param ldshDTO
	 *            The details of the Ldsh to save or create.
	 * @return The Id of the newly created or saved Ldsh.
	 */
	@POST
	@JWTNeeded
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ServiceMessage saveLdsh(LdshDTO ldshDTO) throws ServiceException{
		ldshService.saveLdsh(ldshDTO);
		return new ServiceMessage(Constants.MessageCode.LDSH_SAVE_SUCCESS);
	}

	/**
	 * Deletes a specific Ldsh by Id.
	 * 
	 * @param ldshId
	 *            The Id of the Ldsh to delete.
	 * @throws Exception 
	 */
	@DELETE
	@JWTNeeded
	@Path("/{id}")
	public ServiceMessage deleteLdsh(@PathParam("id") Long ldshId) throws Exception {
		ldshService.deleteLdsh(ldshId);
		return new ServiceMessage(Constants.MessageCode.LDSH_DELETE_SUCCESS);

	}

	/**
	 * Checks if a given Ldsh token is valid or not.
	 * 
	 * @param ldshToken
	 *            The Ldsh token to check for validity.
	 * @return Returns 204 when the token is valid, or 401 otherwise.
	 */
	@GET
	@Path("/token/{id}")
	public Response validateToken(@PathParam("id") String ldshToken) {
		if (ldshService.validateLdshToken(ldshToken)) {
			return Response.noContent().build();
		} else {
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}
	
	@GET
	@JWTNeeded
	@Path("/index/{id}")
	public ServiceMessage indexLdsh(@PathParam("id") Long ldshId) throws UnsupportedEncodingException {
		LdshDTO ldsh = ldshService.getLdsh(ldshId);
		LdshIndexDTO ldshIndexDTO = awiscIndexingService.getLdshIndexDTO(ldsh);
		awiscIndexingService.index(ldshIndexDTO);
		return new ServiceMessage(Constants.MessageCode.LDSH_INDEXING_SUCCESS);

	}

}
