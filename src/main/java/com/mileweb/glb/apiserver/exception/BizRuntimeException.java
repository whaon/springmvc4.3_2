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
