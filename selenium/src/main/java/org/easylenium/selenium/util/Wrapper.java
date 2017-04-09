package org.easylenium.selenium.util;

import org.easylenium.selenium.settings.Timeout;
import org.easylenium.selenium.windows.WindowsBrowserManager;

public class Wrapper
{
	private Timeout timeoutDefault;

	private WindowsBrowserManager windowsBrowserManager;

	public Wrapper(WindowsBrowserManager windowsBrowserManager, Timeout timeoutDefault)
	{
		this.windowsBrowserManager = windowsBrowserManager;
		this.timeoutDefault = timeoutDefault;
	}

	public Timeout getTimeoutDefault()
	{
		return timeoutDefault;
	}

	public WindowsBrowserManager getWindowsBrowserManager()
	{
		return windowsBrowserManager;
	}
}
