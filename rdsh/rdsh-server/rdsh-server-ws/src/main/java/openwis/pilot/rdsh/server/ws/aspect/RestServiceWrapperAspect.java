package openwis.pilot.rdsh.server.ws.aspect;

import java.util.logging.Logger;

import javax.ws.rs.core.Response;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import openwis.pilot.rdsh.server.common.dto.ServiceMessage;
import openwis.pilot.rdsh.server.common.exception.ServiceException;
import openwis.pilot.rdsh.server.common.util.Constants;
import openwis.pilot.rdsh.server.common.util.Constants.ErrorCode;
import openwis.pilot.rdsh.server.manager.util.JwtUtil;
import openwis.pilot.rdsh.server.ws.util.HasHttpHeaders;
import openwis.pilot.rdsh.server.ws.util.WebUtil;

@Aspect
public class RestServiceWrapperAspect {

	private static final Logger logger = Logger.getLogger(RestServiceWrapperAspect.class.getName());

	@Pointcut("execution(@openwis.pilot.rdsh.server.common.annotation.RestServiceWrapper * *(..))")
	public void annotation() {
	}

	@Around("annotation()")
	public Object wrapRestService(ProceedingJoinPoint joinPoint) throws ServiceException {

		try {
			HasHttpHeaders hhh = (HasHttpHeaders) joinPoint.getTarget();

			String token = null;

			try {
				token = WebUtil.getXAuthorizationToken(hhh.getHttpHeaders());
			} catch (ServiceException se) {
				if (ErrorCode.UNAUTHORIZED.equals(se.getErrorCode())) {
					ServiceMessage sm = new ServiceMessage().setType(Constants.MessageType.ERROR)
							.setCode(Constants.ErrorCode.UNAUTHORIZED);
					return WebUtil.buildResponse(sm);
				}

				throw se;
			}

			if (!JwtUtil.istokenValid(token)) {
				ServiceMessage sm = new ServiceMessage().setType(Constants.MessageType.ERROR)
						.setCode(Constants.ErrorCode.FORBIDDEN);
				return WebUtil.buildResponse(sm);
			}

			// Run the actual code
			Object o = joinPoint.proceed();
			
			return WebUtil.buildResponse(o);

		} catch (Throwable t) {
			ServiceMessage sm = new ServiceMessage().setType(Constants.MessageType.ERROR)
					.setCode(Constants.ErrorCode.UNEXPECTED_ERROR);

			return WebUtil.buildResponse(sm);
		}

	}

}
