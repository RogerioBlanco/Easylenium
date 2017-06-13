package org.easylenium.selenium.page;

import static java.lang.String.format;

import java.util.concurrent.TimeUnit;

import org.easylenium.core.executor.ExecutorData;
import org.easylenium.selenium.page.action.Actions;
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

	public Actions by(By selector)
	{
		return new Actions(windowsManager.current(), timeout, selector);
	}

	public Actions css(String css, Object... args)
	{
		return css(format(css, args));
	}

	public Actions xpath(String xpath)
	{
		return by(By.xpath(xpath));
	}

	public Actions xpath(String xpath, Object... args)
	{
		return xpath((format(xpath, args)));
	}

	public Actions id(String id)
	{
		return by(By.id(id));
	}

	public Actions linkText(String linkText)
	{
		return by(By.linkText(linkText));
	}

	public Actions linkText(String linkText, Object... args)
	{
		return linkText(format(linkText, args));
	}

	public Actions partialLinkText(String partialLinkText)
	{
		return by(By.partialLinkText(partialLinkText));
	}

	public Actions partialLinkText(String partialLinkText, Object... args)
	{
		return partialLinkText(format(partialLinkText, args));
	}

	public Actions className(String className)
	{
		return by(By.className(className));
	}

	public Actions className(String className, Object... args)
	{
		return className(format(className, args));
	}

	public Actions tagName(String tagName)
	{
		return by(By.tagName(tagName));
	}

	public Actions tagName(String tagName, Object... args)
	{
		return tagName(format(tagName, args));
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
