package org.easylenium.core.xml.exception;

public class TagUnequalsException extends RequirementException
{

	private static final long serialVersionUID = 6023139398406389334L;

	public TagUnequalsException(String message)
	{
		super(message);
	}

	public TagUnequalsException(String message, Object... args)
	{
		this(String.format(message, args));
	}

}
