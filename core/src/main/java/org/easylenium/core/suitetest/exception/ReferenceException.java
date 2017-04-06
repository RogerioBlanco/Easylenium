package org.easylenium.core.suitetest.exception;

public class ReferenceException extends RuntimeException {

	private static final long serialVersionUID = 7978122939001246119L;

	public ReferenceException(String message) {
		super(message);
	}

	public ReferenceException(String message, Object... args) {
		this(String.format(message, args));
	}
}
