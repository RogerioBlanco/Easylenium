package org.easylenium.selenium.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public enum FactoryBrowserDefault implements FactoryBrowser
{
	FIREFOX
	{
		@Override
		public WebDriver newInstance()
		{
			return new FirefoxDriver();
		}
	},
	CHROME {
		@Override
		public WebDriver newInstance()
		{
			return new ChromeDriver();
		}
	},
	EDGE {
		@Override
		public WebDriver newInstance()
		{
			return new EdgeDriver();
		}
	};

	public abstract WebDriver newInstance();

}
