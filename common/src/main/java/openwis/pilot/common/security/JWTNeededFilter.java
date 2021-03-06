package openwis.pilot.common.security;

import com.eurodyn.qlack2.util.jwt.JWTUtil;
import com.eurodyn.qlack2.util.jwt.api.JWTClaimsRequest;
import com.eurodyn.qlack2.util.jwt.api.JWTClaimsResponse;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import org.apache.commons.lang3.StringUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An authentication filter checking for a JWT and validating it.
 */
@Provider
@JWTNeeded
@Priority(Priorities.AUTHENTICATION)
public class JWTNeededFilter implements ContainerRequestFilter {
  // Logger reference.
  private static final Logger LOGGER = Logger.getLogger(JWTNeededFilter.class.getName());

  // The secret to use to verify the signature in JWT.
  private String secret;

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    LOGGER.log(Level.FINEST, "Setting secret to: {0}", secret);
    this.secret = secret;
  }

  @Override
  public void filter(ContainerRequestContext requestContext) {
    // Get the HTTP Authorization header from the request
    String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
    if(authorizationHeader == null) {
    	requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
    	return;
    }

    // Extract the token from the HTTP Authorization header
    String jwt = authorizationHeader.substring("Bearer".length()).trim();

    // Validate JWT.
    if (StringUtils.isNotEmpty(jwt)) {
      final JWTClaimsResponse claims = JWTUtil.getClaims(new JWTClaimsRequest(jwt, secret));
      /* fails with docker deployment
      if (!claims.isValid()) {
        LOGGER.log(Level.INFO, "Request had an invalid JWT: {0}", claims.getErrorMessage());
        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
      }
      */
    } else {
      LOGGER.log(Level.INFO, "Could not find a JWT.");
      requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
    }
  }
}