package org.easylenium.selenium.settings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public enum Browser
{
	FIREFOX{public WebDriver newInstace(){return new FirefoxDriver();}};

	public abstract WebDriver newInstace();
}
