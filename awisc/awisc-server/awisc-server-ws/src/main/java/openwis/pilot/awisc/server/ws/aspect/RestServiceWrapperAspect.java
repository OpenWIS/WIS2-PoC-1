package openwis.pilot.awisc.server.ws.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import openwis.pilot.awisc.server.common.dto.ServiceError;
import openwis.pilot.awisc.server.common.exception.ServiceException;
import openwis.pilot.awisc.server.common.util.Constants;
import openwis.pilot.awisc.server.common.util.Constants.ErrorCode;
import openwis.pilot.awisc.server.manager.util.JwtUtil;
import openwis.pilot.awisc.server.ws.util.HasHttpHeaders;
import openwis.pilot.awisc.server.ws.util.WebUtil;

@Aspect
public class RestServiceWrapperAspect {

	private static final Logger logger = Logger.getLogger(RestServiceWrapperAspect.class.getName());

	@Pointcut("execution(@openwis.pilot.awisc.server.common.annotation.RestServiceWrapper * *(..))")
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
					ServiceError em = new ServiceError(Constants.ErrorCode.UNAUTHORIZED);
					return WebUtil.buildResponse(em);
				}

				throw se;
			}

			if (!JwtUtil.istokenValid(token)) {
				ServiceError em = new ServiceError(Constants.ErrorCode.FORBIDDEN);
				return WebUtil.buildResponse(em);
			}

			// Run the actual code
			Object o = joinPoint.proceed();
			
			return WebUtil.buildResponse(o);

		} catch (Throwable t) {
			ServiceError em = new ServiceError(Constants.ErrorCode.UNEXPECTED_ERROR);

			return WebUtil.buildResponse(em);
		}

	}

}
