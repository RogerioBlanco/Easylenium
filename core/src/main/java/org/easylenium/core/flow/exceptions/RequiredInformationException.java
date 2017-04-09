package org.easylenium.core.flow.exceptions;

public class RequiredInformationException extends RuntimeException
{
	private static final long serialVersionUID = -2576301795080579710L;

	public RequiredInformationException(String message)
	{
		super(message);
	}

	public RequiredInformationException(String message, Object... args)
	{
		this(String.format(message, args));
	}
}
