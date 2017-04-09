package org.easylenium.core.flow.exceptions;

public class FieldValueException extends RuntimeException
{
	private static final long serialVersionUID = -7135329947871058529L;

	public FieldValueException(String message)
	{
		super(message);
	}

	public FieldValueException(String message, Object... args)
	{
		this(String.format(message, args));
	}

	public FieldValueException(Throwable throwable, String message)
	{
		super(message, throwable);
	}

	public FieldValueException(Throwable throwable, String message, Object... args)
	{
		this(throwable, String.format(message, args));
	}
}
