package org.easylenium.selenium.page.action;

import org.easylenium.selenium.settings.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageActionComplex
{
	private WebDriver current;

	private By context;

	private By selector;

	private WebDriverWait wait;

	public PageActionComplex(WebDriver current, Timeout timeout, By context, By selector)
	{
		this.current = current;
		this.context = context;
		this.selector = selector;
		this.wait = new WebDriverWait(current, timeout.getTimeUnit().toSeconds(timeout.getTime()));
	}

}
