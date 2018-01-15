package openwis.pilot.awisc.server.common.exception;

import openwis.pilot.awisc.server.common.util.Constants.ErrorCode;

public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -406900182867213737L;

	protected ErrorCode errorCode;

	public ServiceException(ErrorCode errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public ServiceException(String message) {
		super(message);
		errorCode = ErrorCode.UNEXPECTED_ERROR;
	}

	public ServiceException(Throwable t) {
		super(t);
		errorCode = ErrorCode.UNEXPECTED_ERROR;
	}

	public static void rethrow(Throwable t) throws ServiceException {
		if (ServiceException.class.isAssignableFrom(t.getClass())) {
			throw (ServiceException) t;
		}
		throw new ServiceException(t);
	}
	
	public static ServiceException wrap(Throwable t){
		try {
			rethrow(t);
		}
		catch (ServiceException se) {
			return se;
		}
		return null;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

}
