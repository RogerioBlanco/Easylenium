package org.easylenium.core.executor.exception;

public class ExpectedException extends RuntimeException {

	private static final long serialVersionUID = -8818962413420654139L;

	public ExpectedException() {
		super();
	}

	public ExpectedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ExpectedException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExpectedException(String message) {
		super(message);
	}

	public ExpectedException(Throwable cause) {
		super(cause);
	}

}
