package openwis.pilot.awisc.server.ws.aspect;

import java.util.logging.Logger;

import javax.ws.rs.core.Response;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import openwis.pilot.awisc.server.common.dto.ServiceMessage;
import openwis.pilot.awisc.server.common.exception.ServiceException;
import openwis.pilot.awisc.server.common.util.Constants;
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
			ServiceMessage sm = new ServiceMessage().setType(Constants.MessageType.ERROR)
					.setCode(se.getErrorCode());

			return WebUtil.buildResponse(sm);
		}

	}

}
