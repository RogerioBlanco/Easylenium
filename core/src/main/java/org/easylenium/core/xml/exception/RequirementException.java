package org.easylenium.core.xml.exception;

public class RequirementException extends RuntimeException {

	private static final long serialVersionUID = 4484174857934373839L;

	public RequirementException() {
		super();
	}

	public RequirementException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RequirementException(String message, Throwable cause) {
		super(message, cause);
	}

	public RequirementException(String message) {
		super(message);
	}

	public RequirementException(Throwable cause) {
		super(cause);
	}
	
	public RequirementException(String message, Object... args){
		super(String.format(message, args));
	}
	
	public RequirementException(Throwable cause, String message, Object... args){
		super(String.format(message, args), cause);
	}

}
