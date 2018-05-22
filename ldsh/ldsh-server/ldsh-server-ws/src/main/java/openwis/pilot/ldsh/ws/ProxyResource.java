package openwis.pilot.ldsh.ws;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Singleton;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.provider.json.JSONProvider;
import org.apache.cxf.rs.security.cors.CorsHeaderConstants;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;

import openwis.pilot.ldsh.common.dto.ForwardRequest;
import openwis.pilot.ldsh.ws.ProxyResource.Method;

@Singleton
@Path("/proxy")
public class ProxyResource {

	private static final Logger logger = Logger.getLogger(ProxyResource.class.getName());

	protected static class Method {
		public static String POST = "POST";
		public static String GET = "GET";
	}

	private Response getResponse(Response originalResponse) {
		return Response.fromResponse(originalResponse).header(CorsHeaderConstants.HEADER_AC_ALLOW_ORIGIN, "*")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_METHODS, "GET,HEAD,OPTIONS,POST,PUT")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_CREDENTIALS, "true")
				.header(CorsHeaderConstants.HEADER_AC_ALLOW_HEADERS,
						"Access-Control-Allow-Headers, origin, content-type, accept, authorization, Origin, Content-type, Accept, Authorization")
				.build();
	}

	@POST
	@Path("/forward")
	@CrossOriginResourceSharing(allowAllOrigins = true)
	public Response forward(ForwardRequest request) {
		try {

			String method = request.getMethod();
			String uri = request.getUri();
			String data = request.getData();

			List<JSONProvider<?>> providers = new ArrayList<JSONProvider<?>>();
			JSONProvider<?> jsonProvider = new JSONProvider<>();
			jsonProvider.setDropRootElement(true);
			jsonProvider.setDropCollectionWrapperElement(true);
			jsonProvider.setSerializeAsArray(true);
			jsonProvider.setSupportUnwrapped(true);
			providers.add(jsonProvider);

			WebClient client = WebClient.create(uri, providers);

			if (Method.GET.equalsIgnoreCase(method)) {
				Response r = client.get();
				logger.info("RESPONSE STATUS FROM REMOTE SYSTEM: " + r.getStatus());
				String stringResponse = r.readEntity(String.class);
				logger.info("RESPONSE STRING FROM REMOTE SYSTEM: " + stringResponse);
				return getResponse(r);
			}
			if (Method.POST.equalsIgnoreCase(method)) {
				client = client.accept("application/json").type("application/json");
				Response r = client.post(data);
				return getResponse(r);
			}
			return null;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "ERROR", e);
			return Response.status(Status.NO_CONTENT).build();
		}
	}
}
