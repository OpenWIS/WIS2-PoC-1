package openwis.pilot.ldsh.ws;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import java.io.File;

import openwis.pilot.ldsh.manager.service.FileService;

import org.apache.cxf.rs.security.cors.CorsHeaderConstants;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.ops4j.pax.cdi.api.OsgiService;

@Singleton
public class FileResource {

    private static final Logger logger = Logger.getLogger(FileResource.class.getName());

    @Context
    private HttpHeaders headers;

    @Inject
    @OsgiService
    private FileService fileService;
    

    @GET
    @Path("/download/{relative}/{prefix}")
    @CrossOriginResourceSharing(allowAllOrigins = true)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    
    public Response getFile(@PathParam("relative") String relativeUrl, @PathParam("prefix") String prefix) {
        try {
System.out.println("@getFile prefix "+prefix +" relative "+relativeUrl );
    	File file = fileService.getFile(relativeUrl, prefix );
    	logger.log(Level.INFO, "Downloading "+file.getName());
        return Response.ok(file , MediaType.APPLICATION_OCTET_STREAM)
      		 .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"" )
      		 	.header(CorsHeaderConstants.HEADER_AC_ALLOW_ORIGIN, "*")
                .header(CorsHeaderConstants.HEADER_AC_ALLOW_METHODS, "GET,HEAD,OPTIONS,POST,PUT")
                .header(CorsHeaderConstants.HEADER_AC_ALLOW_CREDENTIALS, "true")
                .header(CorsHeaderConstants.HEADER_AC_ALLOW_HEADERS,
                        "Access-Control-Allow-Headers, origin, content-type, accept, authorization, Origin, Content-type, Accept, Authorization")
                .build();
        } catch (Exception e) {
            return Response.status(Status.NO_CONTENT).build();
          }
    }
 }
