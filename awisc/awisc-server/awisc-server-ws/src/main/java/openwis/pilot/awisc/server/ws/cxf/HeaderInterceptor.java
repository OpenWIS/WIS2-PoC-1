package openwis.pilot.awisc.server.ws.cxf;

import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.rs.security.cors.CorsHeaderConstants;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

import openwis.pilot.awisc.server.ws.util.WebConstants;

public class HeaderInterceptor extends AbstractPhaseInterceptor<Message>{
	
	public HeaderInterceptor() {
	      super(Phase.PRE_PROTOCOL);
	   }

	@Override
	public void handleMessage(Message message) throws Fault {
		HttpServletResponse response = (HttpServletResponse) message.get(AbstractHTTPDestination.HTTP_RESPONSE);
		response.addHeader(CorsHeaderConstants.HEADER_AC_EXPOSE_HEADERS, WebConstants.Headers.AUTHORIZATION);
		response.addHeader(CorsHeaderConstants.HEADER_AC_ALLOW_HEADERS, WebConstants.Headers.AUTHORIZATION);
	}

}
