package openwis.pilot.rdsh.server.ws.aspect;

import openwis.pilot.rdsh.server.common.dto.ServiceMessage;
import openwis.pilot.rdsh.server.common.exception.ServiceException;
import openwis.pilot.rdsh.server.common.util.Constants;
import openwis.pilot.rdsh.server.ws.util.WebUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.logging.Logger;

@Aspect
public class ServiceExceptionHandlerAspect {

	private static final Logger logger = Logger.getLogger(ServiceExceptionHandlerAspect.class.getName());

	@Pointcut("execution(@openwis.pilot.rdsh.server.common.annotation.ServiceExceptionHandler * *(..))")
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
