package org.easylenium.core.xml.exception;

public class AttributeException extends RequirementException
{

	private static final long serialVersionUID = 2639217648879548330L;

	public AttributeException(String message)
	{
		super(message);
	}

	public AttributeException(String message, Object... args)
	{
		this(String.format(message, args));
	}
}
