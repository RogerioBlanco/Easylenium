package org.easylenium.selenium.page.action;

import org.easylenium.selenium.settings.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PageActionSimple
{
	private By selector;

	private WebDriver webDriver;

	private WebDriverWait wait;
	
	public PageActionSimple(WebDriver current, Timeout timeout, By selector)
	{
		this.webDriver = current;
		this.selector = selector;
		this.wait = new WebDriverWait(current, timeout.getTimeUnit().toSeconds(timeout.getTime()));
	}

}
