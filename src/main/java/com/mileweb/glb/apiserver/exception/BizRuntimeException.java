package com.mileweb.glb.apiserver.exception;

/**
 * 
 * @author tongh
 *
 */
public class BizRuntimeException extends RuntimeException {
	private ExceptionWrapper exceptionWrapper;
	private Object[] param;

	public BizRuntimeException(ExceptionWrapper exceptionWrapper, Object[] param) {
		super();
		this.exceptionWrapper = exceptionWrapper;
		this.param = param;
	}

	public BizRuntimeException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BizRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public BizRuntimeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BizRuntimeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BizRuntimeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public ExceptionWrapper getExceptionWrapper() {
		return exceptionWrapper;
	}

	public void setExceptionWrapper(ExceptionWrapper exceptionWrapper) {
		this.exceptionWrapper = exceptionWrapper;
	}

	public Object[] getParam() {
		return param;
	}

	public void setParam(Object[] param) {
		this.param = param;
	}
}
