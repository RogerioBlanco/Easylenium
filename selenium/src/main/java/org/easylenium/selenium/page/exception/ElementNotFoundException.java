package org.easylenium.selenium.page.exception;

public class ElementNotFoundException extends RuntimeException
{
	private static final long serialVersionUID = 8504842168498665128L;

	public ElementNotFoundException(String message)
	{
		super(message);
	}

	public ElementNotFoundException(String message, Object... args)
	{
		this(String.format(message, args));
	}

}
