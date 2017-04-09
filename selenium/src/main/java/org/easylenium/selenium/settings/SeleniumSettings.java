package org.easylenium.selenium.settings;

import org.easylenium.core.settings.Settings;
import org.easylenium.selenium.settings.exception.SeleniumSettingsValidationException;

public class SeleniumSettings extends Settings
{

	private Browser browser;

	private TimeoutSeleniumWrapper timeoutManager;

	public SeleniumSettings(Browser browser)
	{
		this.browser = browser;
	}

	public void validate()
	{
		super.validate();

		if (browser == null)
			throw new SeleniumSettingsValidationException("It must be selected at least one browser.");

		if (timeoutManager == null)
			throw new SeleniumSettingsValidationException("You must configure the default timeout.");
	}

	public Browser getBrowser()
	{
		return browser;
	}

	public void setTimeoutManager(TimeoutSeleniumWrapper timeoutManager)
	{
		this.timeoutManager = timeoutManager;
	}

	public TimeoutSeleniumWrapper getTimeout()
	{
		return timeoutManager;
	}

}
