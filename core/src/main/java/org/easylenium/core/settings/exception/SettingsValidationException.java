package org.easylenium.core.settings.exception;

/**
 * @author rogerionunes
 *
 */
public class SettingsValidationException extends RuntimeException
{

	private static final long serialVersionUID = -8164830167006497413L;

	public SettingsValidationException(String message)
	{
		super(message);
	}
	
	public SettingsValidationException(String message, Object... args)
	{
		this(String.format(message, args));
	}

}
