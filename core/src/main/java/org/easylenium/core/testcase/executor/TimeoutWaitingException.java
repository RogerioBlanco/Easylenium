package org.easylenium.core.testcase.executor;

import org.easylenium.core.testcase.executor.exception.TimeoutException;

public class TimeoutWaitingException extends TimeoutException {

	private static final long serialVersionUID = -380681833989108925L;

	public TimeoutWaitingException() {
		super();
	}

	public TimeoutWaitingException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TimeoutWaitingException(String message, Throwable cause) {
		super(message, cause);
	}

	public TimeoutWaitingException(String message) {
		super(message);
	}

	public TimeoutWaitingException(Throwable cause) {
		super(cause);
	}

}
