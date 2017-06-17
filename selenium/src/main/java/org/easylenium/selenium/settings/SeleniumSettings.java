package org.easylenium.selenium.settings;

import org.easylenium.core.settings.Settings;
import org.easylenium.selenium.browser.FactoryBrowser;
import org.easylenium.selenium.settings.exception.SeleniumSettingsValidationException;

public class SeleniumSettings extends Settings
{

	private FactoryBrowser factoryBrowser;

	private TimeoutSeleniumWrapper timeoutManager;

	public SeleniumSettings(FactoryBrowser factory)
	{
		this.factoryBrowser = factory;
	}

	public void validate()
	{
		super.validate();

		if (factoryBrowser == null)
			throw new SeleniumSettingsValidationException("It must be implemented a factory.");

		if (timeoutManager == null)
			throw new SeleniumSettingsValidationException("You must configure the default timeout.");
	}

	public FactoryBrowser getFactoryBrowser()
	{
		return factoryBrowser;
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
