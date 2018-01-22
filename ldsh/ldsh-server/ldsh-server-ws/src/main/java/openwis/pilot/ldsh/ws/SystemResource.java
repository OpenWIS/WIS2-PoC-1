package openwis.pilot.ldsh.ws;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import openwis.pilot.ldsh.common.dto.RemoteSystemDTO;
import openwis.pilot.ldsh.common.dto.SysPropertyDTO;
import openwis.pilot.ldsh.manager.service.PollingService;
import openwis.pilot.ldsh.manager.service.SystemService;

import org.apache.cxf.rs.security.cors.CorsHeaderConstants;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.ops4j.pax.cdi.api.OsgiService;


@Singleton
public class SystemResource {
    private static final Logger logger = Logger.getLogger(SystemResource.class.getName());


	   @Context
	    private HttpHeaders headers;

	    @Inject
	    @OsgiService
	    private SystemService systemService;
	    
	    
	    @Inject
	    @OsgiService
	    private PollingService polingService;
	    
	    
	    @POST
	    @Path("/saveSettings")
	    @CrossOriginResourceSharing(allowAllOrigins = true)
	    @Produces(MediaType.APPLICATION_JSON)
	    @Consumes(MediaType.APPLICATION_JSON)
	    public Response storeSettings(SysPropertyDTO sysPropertyDto) {
	    	logger.log(Level.INFO, "Saving ....: "+ sysPropertyDto.toString());
	        return Response.ok(systemService.saveSystemProperty(sysPropertyDto))
	        .header(CorsHeaderConstants.HEADER_AC_ALLOW_ORIGIN, "*")
	                .header(CorsHeaderConstants.HEADER_AC_ALLOW_METHODS, "GET,HEAD,OPTIONS,POST,PUT")
	                .header(CorsHeaderConstants.HEADER_AC_ALLOW_CREDENTIALS, "true")
	                .header(CorsHeaderConstants.HEADER_AC_ALLOW_HEADERS,
	                        "Access-Control-Allow-Headers, origin, content-type, accept, authorization, Origin, Content-type, Accept, Authorization")
	                .build();
	    }
	    
	    
//	    @POST
//	    @Path("/getSettings")
//	    @CrossOriginResourceSharing(allowAllOrigins = true)
//	    @Produces(MediaType.APPLICATION_JSON)
//	    @Consumes(MediaType.APPLICATION_JSON)
//	    public Response fetchSettings() {
//	    	
////	    	logger.log(Level.INFO, "Saving ....: "+ sysProperty.getName());
//
//	    	return Response.ok(systemService.getAllSystemProperties())
//	        .header(CorsHeaderConstants.HEADER_AC_ALLOW_ORIGIN, "*")
//	                .header(CorsHeaderConstants.HEADER_AC_ALLOW_METHODS, "GET,HEAD,OPTIONS,POST,PUT")
//	                .header(CorsHeaderConstants.HEADER_AC_ALLOW_CREDENTIALS, "true")
//	                .header(CorsHeaderConstants.HEADER_AC_ALLOW_HEADERS,
//	                        "Access-Control-Allow-Headers, origin, content-type, accept, authorization, Origin, Content-type, Accept, Authorization")
//	                .build();
//	    }
	    
		  @GET
		    @Path("/getSettings")
		    @CrossOriginResourceSharing(allowAllOrigins = true)
		    @Produces(MediaType.APPLICATION_JSON)
		    public Response fetchSettings() {
		        return Response.ok( systemService.getAllSystemProperties())
		        .header(CorsHeaderConstants.HEADER_AC_ALLOW_ORIGIN, "*")
		        .header(CorsHeaderConstants.HEADER_AC_ALLOW_METHODS, "GET,HEAD,OPTIONS,POST,PUT")
		        .header(CorsHeaderConstants.HEADER_AC_ALLOW_CREDENTIALS, "true")
		        .header(CorsHeaderConstants.HEADER_AC_ALLOW_HEADERS, "origin, content-type, accept, authorization, Origin, Content-type, Accept, Authorization")
		        .build();
		    }
		  
		  
		  @GET
		  @Path("/startPolling")
		  @CrossOriginResourceSharing(allowAllOrigins = true)
		  @Produces(MediaType.APPLICATION_JSON)
		  public Response startPolling() {
		      return Response.ok( polingService.startPolling())
		      .header(CorsHeaderConstants.HEADER_AC_ALLOW_ORIGIN, "*")
		      .header(CorsHeaderConstants.HEADER_AC_ALLOW_METHODS, "GET,HEAD,OPTIONS,POST,PUT")
		      .header(CorsHeaderConstants.HEADER_AC_ALLOW_CREDENTIALS, "true")
		      .header(CorsHeaderConstants.HEADER_AC_ALLOW_HEADERS, "origin, content-type, accept, authorization, Origin, Content-type, Accept, Authorization")
		      .build();
		  }
		  
		  

	    
	    
	    
	    @POST
	    @Path("/saveRemote")
	    @CrossOriginResourceSharing(allowAllOrigins = true)
	    @Produces(MediaType.APPLICATION_JSON)
	    @Consumes(MediaType.APPLICATION_JSON)
	    public Response userVerification(RemoteSystemDTO remoteSystem) {

	    	logger.log(Level.INFO, "Saving ....: "+ remoteSystem.getName());
	    	
	        return Response.ok(systemService.saveSystem(remoteSystem))
	        .header(CorsHeaderConstants.HEADER_AC_ALLOW_ORIGIN, "*")
	                .header(CorsHeaderConstants.HEADER_AC_ALLOW_METHODS, "GET,HEAD,OPTIONS,POST,PUT")
	                .header(CorsHeaderConstants.HEADER_AC_ALLOW_CREDENTIALS, "true")
	                .header(CorsHeaderConstants.HEADER_AC_ALLOW_HEADERS,
	                        "Access-Control-Allow-Headers, origin, content-type, accept, authorization, Origin, Content-type, Accept, Authorization")
	                .build();
	    }
	    
	    
	    @GET
	    @Path("/getAwisc")
	    @CrossOriginResourceSharing(allowAllOrigins = true)
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response fetchAiwsc() {
	        return Response.ok( systemService.getAwisc())
	        .header(CorsHeaderConstants.HEADER_AC_ALLOW_ORIGIN, "*")
	        .header(CorsHeaderConstants.HEADER_AC_ALLOW_METHODS, "GET,HEAD,OPTIONS,POST,PUT")
	        .header(CorsHeaderConstants.HEADER_AC_ALLOW_CREDENTIALS, "true")
	        .header(CorsHeaderConstants.HEADER_AC_ALLOW_HEADERS, "origin, content-type, accept, authorization, Origin, Content-type, Accept, Authorization")
	        .build();
	    }
	    
	    
	    @GET
	    @Path("/getRdsh")
	    @CrossOriginResourceSharing(allowAllOrigins = true)
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response fetchRdsh() {
	        return Response.ok( systemService.getRdsh())
	        .header(CorsHeaderConstants.HEADER_AC_ALLOW_ORIGIN, "*")
	        .header(CorsHeaderConstants.HEADER_AC_ALLOW_METHODS, "GET,HEAD,OPTIONS,POST,PUT")
	        .header(CorsHeaderConstants.HEADER_AC_ALLOW_CREDENTIALS, "true")
	        .header(CorsHeaderConstants.HEADER_AC_ALLOW_HEADERS, "origin, content-type, accept, authorization, Origin, Content-type, Accept, Authorization")
	        .build();
	    }
	    
	    
}
