package org.easylenium.selenium.settings;

import java.util.concurrent.TimeUnit;

public class TimeoutSeleniumWrapper
{
	private static final Timeout DEFAULT_TIMEOUT = new Timeout(TimeUnit.SECONDS, 2L);

	public static final TimeoutSeleniumWrapper DEFAULT = new TimeoutSeleniumWrapper(DEFAULT_TIMEOUT, DEFAULT_TIMEOUT, DEFAULT_TIMEOUT, DEFAULT_TIMEOUT);
	
	private Timeout implicitlyWait;

	private Timeout pageLoadTimeout;

	private Timeout scriptTimeout;

	private Timeout defaultTimeout;

	public TimeoutSeleniumWrapper(Timeout implicitlyWait, Timeout pageLoadTimeout, Timeout scriptTimeout, Timeout defaultTimeout)
	{
		this.implicitlyWait = implicitlyWait;
		this.pageLoadTimeout = pageLoadTimeout;
		this.scriptTimeout = scriptTimeout;
		this.defaultTimeout = defaultTimeout;
	}

	public Timeout getImplicitlyWait()
	{
		return implicitlyWait;
	}

	public Timeout getPageLoadTimeout()
	{
		return pageLoadTimeout;
	}

	public Timeout getScriptTimeout()
	{
		return scriptTimeout;
	}

	public Timeout getDefaultTimeout()
	{
		if (defaultTimeout != null)
			return DEFAULT_TIMEOUT;

		return defaultTimeout;
	}
}
