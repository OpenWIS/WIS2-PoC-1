package openwis.pilot.awisc.server.ws.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import openwis.pilot.awisc.server.common.dto.ServiceError;
import openwis.pilot.awisc.server.common.exception.ServiceException;
import openwis.pilot.awisc.server.ws.util.WebUtil;

@Aspect
public class ServiceExceptionHandlerAspect {

	private static final Logger logger = Logger.getLogger(ServiceExceptionHandlerAspect.class.getName());

	@Pointcut("execution(@openwis.pilot.awisc.server.common.annotation.ServiceExceptionHandler * *(..))")
	public void annotation() {
	}

	@Around("annotation()")
	public Object serviceExceptionHandler(ProceedingJoinPoint joinPoint) {

		try {
			// Run the actual code
			Object o = joinPoint.proceed();

			return WebUtil.buildResponse(o);

		}
		catch (Throwable t) {
			ServiceException se = ServiceException.wrap(t);
			ServiceError ser = new ServiceError(se.getErrorCode());

			return WebUtil.buildResponse(ser);
		}

	}

}
