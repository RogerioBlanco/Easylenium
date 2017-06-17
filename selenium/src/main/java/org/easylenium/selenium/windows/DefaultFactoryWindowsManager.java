package org.easylenium.selenium.windows;

import org.easylenium.selenium.settings.SeleniumSettings;
import org.easylenium.selenium.settings.Timeout;
import org.easylenium.selenium.settings.TimeoutSeleniumWrapper;
import org.openqa.selenium.WebDriver;

public class DefaultFactoryWindowsManager implements FactoryWindowsManager
{
	private SeleniumSettings settings;

	public DefaultFactoryWindowsManager(SeleniumSettings settings)
	{
		this.settings = settings;
	}

	public WindowsBrowserManager newWindowsManager()
	{
		WebDriver webDriver = settings.getFactoryBrowser().newInstance();

		setupTimeoutWebDriver(webDriver, settings.getTimeout());

		addShutdownHook(webDriver);

		return new WindowsBrowserManager(webDriver);
	}

	private void setupTimeoutWebDriver(WebDriver webDriver, TimeoutSeleniumWrapper timeoutWrapper)
	{
		Timeout implicitlyWait = timeoutWrapper.getImplicitlyWait();
		webDriver.manage().timeouts().implicitlyWait(implicitlyWait.getTime(), implicitlyWait.getTimeUnit());

		Timeout scriptTimeout = timeoutWrapper.getScriptTimeout();
		webDriver.manage().timeouts().setScriptTimeout(scriptTimeout.getTime(), scriptTimeout.getTimeUnit());

		Timeout pageLoadTimeout = timeoutWrapper.getPageLoadTimeout();
		webDriver.manage().timeouts().pageLoadTimeout(pageLoadTimeout.getTime(), pageLoadTimeout.getTimeUnit());
	}

	private void addShutdownHook(final WebDriver webDriver)
	{
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run()
			{
				webDriver.quit();
			}
		});
	}

}
