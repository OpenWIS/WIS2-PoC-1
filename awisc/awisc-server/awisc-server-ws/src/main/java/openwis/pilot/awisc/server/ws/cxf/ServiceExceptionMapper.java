package openwis.pilot.awisc.server.ws.cxf;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import openwis.pilot.awisc.server.common.dto.ServiceError;
import openwis.pilot.awisc.server.common.exception.ServiceException;
import openwis.pilot.awisc.server.ws.util.WebUtil;

public class ServiceExceptionMapper implements ExceptionMapper<Throwable> {

	private static Logger logger = LoggerFactory.getLogger(ServiceExceptionMapper.class);

	@Override
	public Response toResponse(Throwable t) {

		ServiceException se = ServiceException.wrap(t);
		logger.error("Error code: " + se.getErrorCode(), se);

		ServiceError error = new ServiceError(se.getErrorCode());

		return WebUtil.buildResponse(error);
	}

}
