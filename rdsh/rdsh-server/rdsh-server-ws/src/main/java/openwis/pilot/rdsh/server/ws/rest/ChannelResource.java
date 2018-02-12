package openwis.pilot.rdsh.server.ws.rest;

import java.util.List;

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

import org.ops4j.pax.cdi.api.OsgiService;

import openwis.pilot.common.security.JWTNeeded;

import openwis.pilot.rdsh.server.common.dto.ChannelDTO;
import openwis.pilot.rdsh.server.manager.service.ChannelService;

@Singleton
@Path("/channel")
public class ChannelResource {

	@OsgiService
	@Inject
	private ChannelService channelService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllChannels() {
		return Response.ok(channelService.getAllChannels()).build();
	}

	@POST
	@JWTNeeded
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveChannel(ChannelDTO channelDTO) {
		return Response.ok(channelService.saveChannel(channelDTO)).build();
	}

	@GET
	@JWTNeeded
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getChannel(@PathParam("id") Long id) {
		return Response.ok(channelService.getChannel(id)).build();
	}

	@DELETE
	@JWTNeeded
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteChannel(@PathParam("id") Long id) {
		return Response.ok(channelService.deleteChannel(id)).build();
	}

	@GET
	@JWTNeeded
	@Path("purge/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLDSH(@PathParam("id")Long id){
		return Response.ok(channelService.purgeChannel(id)).build();
	}

}
