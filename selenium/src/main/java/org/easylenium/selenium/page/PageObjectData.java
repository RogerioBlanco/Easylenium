package org.easylenium.selenium.page;

import java.util.concurrent.TimeUnit;

import org.easylenium.core.executor.ExecutorData;
import org.easylenium.selenium.page.action.PageActionComplex;
import org.easylenium.selenium.page.action.PageActionSimple;
import org.easylenium.selenium.settings.Timeout;
import org.easylenium.selenium.util.Wrapper;
import org.easylenium.selenium.windows.WindowsBrowserManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class PageObjectData<T> extends ExecutorData<T>
{
	public WindowsBrowserManager windowsManager;

	private Timeout timeout;

	public PageObjectData(Wrapper wrapper, T data)
	{
		super(data);
		this.windowsManager = wrapper.getWindowsBrowserManager();
		this.timeout = wrapper.getTimeoutDefault();
	}

	public PageActionSimple by(By selector)
	{
		return new PageActionSimple(windowsManager.current(), timeout, selector);
	}

	public PageActionComplex by(By context, By selector)
	{
		return new PageActionComplex(windowsManager.current(), timeout, context, selector);
	}

	public PageActionSimple css(String css)
	{
		return by(By.cssSelector(css));
	}

	public PageActionSimple css(String css, Object... args)
	{
		return css(String.format(css, args));
	}

	public PageActionComplex css(By context, String css)
	{
		return by(context, By.cssSelector(css));
	}

	public PageActionSimple xpath(String xpath)
	{
		return by(By.xpath(xpath));
	}

	public PageActionSimple xpath(String xpath, Object... args)
	{
		return xpath((String.format(xpath, args)));
	}

	public PageActionComplex xpath(By context, String xpath)
	{
		return by(context, By.xpath(xpath));
	}

	public PageActionSimple id(String id)
	{
		return by(By.id(id));
	}

	public PageActionComplex id(By context, String id)
	{
		return by(context, By.id(id));
	}

	public PageActionSimple linkText(String linkText)
	{
		return by(By.linkText(linkText));
	}

	public PageActionSimple linkText(String linkText, Object... args)
	{
		return linkText(String.format(linkText, args));
	}

	public PageActionComplex linkText(By context, String linkText)
	{
		return by(context, By.linkText(linkText));
	}

	public PageActionSimple partialLinkText(String partialLinkText)
	{
		return by(By.partialLinkText(partialLinkText));
	}

	public PageActionSimple partialLinkText(String partialLinkText, Object... args)
	{
		return partialLinkText(String.format(partialLinkText, args));
	}

	public PageActionComplex partialLinkText(By context, String partialLinkText)
	{
		return by(context, By.partialLinkText(partialLinkText));
	}

	public PageActionSimple className(String className)
	{
		return by(By.className(className));
	}

	public PageActionSimple className(String className, Object... args)
	{
		return className(String.format(className, args));
	}

	public PageActionComplex className(By context, String className)
	{
		return by(context, By.className(className));
	}

	public PageActionSimple	tagName(String tagName)
	{
		return by(By.tagName(tagName));
	}

	public PageActionSimple tagName(String tagName, Object... args)
	{
		return tagName(String.format(tagName, args)); 
	}

	public PageActionComplex tagName(By context, String tagName)
	{
		return by(context, By.tagName(tagName));
	}

	public String getTitle()
	{
		return windowsManager.current().getTitle();
	}

	public void goTo(String url)
	{
		windowsManager.current().get(url);
	}

	public void maximizeWindow()
	{
		windowsManager.current().manage().window().maximize();
	}

	public Object executeScript(String script)
	{
		return ((JavascriptExecutor) windowsManager.current()).executeScript(script);
	}

	public Object executeScript(String script, Object... args)
	{
		return ((JavascriptExecutor) windowsManager.current()).executeScript(script, args);
	}

	public Alert waitAlert()
	{
		return waiting().until(ExpectedConditions.alertIsPresent());
	}

	private WebDriverWait waiting()
	{
		return new WebDriverWait(windowsManager.current(), timeout.getTimeUnit().toSeconds(timeout.getTime()));
	}

	public WindowsBrowserManager waitBrowserWindow()
	{
		return waitBrowserWindow(timeout.getTimeUnit(), timeout.getTime());
	}

	public WindowsBrowserManager waitBrowserWindow(TimeUnit unit, Long time)
	{
		return windowsManager.waitNewWindowBrowser(unit, time);
	}

	public Timeout getTimeout()
	{
		return timeout;
	}
}
