package org.easylenium.core.testcase.xml;

public class ClonePropertiesException extends RuntimeException {

	private static final long serialVersionUID = 4830195429780797037L;

	public ClonePropertiesException(Throwable cause, String message, Object... args) {
		super(String.format(message, args), cause);
	}

	
}
