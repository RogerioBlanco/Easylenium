package org.easylenium.selenium.page.action;

import java.util.List;
import java.util.function.Function;

import org.easylenium.selenium.page.exception.ElementNotFoundException;
import org.easylenium.selenium.page.interfaces.FillText;
import org.easylenium.selenium.settings.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageActionSimple implements FillText
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

	public WebElement findElement()
	{
		List<WebElement> elements = findElements();

		for (WebElement element : elements)
			if (element.isDisplayed() && element.isEnabled())
				return element;

		throw new ElementNotFoundException("The element is not visible and enable.");
	}

	public List<WebElement> findElements()
	{
		waitLoadAndVisible();

		return webDriver.findElements(selector);
	}

	public void fill(CharSequence... text)
	{
		findElement().sendKeys(text);
	}

	public FillText clear()
	{
		findElement().clear();

		return this;
	}

	public void click()
	{
		findElement().click();
	}

	public void choose(String text)
	{
		List<WebElement> findElements = findElement().findElements(By.xpath(".//option"));
		for (WebElement element : findElements)
		{
			if (element.isDisplayed() && element.isEnabled() && element.getText().contains(text))
			{
				element.click();
				break;
			}
		}
	}

	public boolean existElement()
	{
		try
		{
			waitUntilDocumentStateIsComplete();

			return !webDriver.findElements(selector).isEmpty();
		} catch (Exception e)
		{
			return Boolean.FALSE;
		}
	}

	public void waittUntilIsVisible()
	{
		wait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver web)
			{
				for (WebElement element : web.findElements(selector))
					if (element.isDisplayed())
						return Boolean.TRUE;

				return Boolean.FALSE;
			}

		});
	}

	public void waitUntilHide()
	{
		wait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver web)
			{
				List<WebElement> elements = web.findElements(selector);
				for (WebElement element : elements)
					if (element.isDisplayed())
						return Boolean.FALSE;

				return Boolean.TRUE;
			}
		});

	}

	public boolean isSelected()
	{
		return findElement().isSelected();
	}

	public boolean isEnable()
	{
		return findElement().isEnabled();
	}

	public String getAttribute(String name)
	{
		return findElement().getAttribute(name);
	}

	public String getText()
	{
		return findElement().getText();
	}

	public void moveToElement()
	{
		new Actions(webDriver).moveToElement(findElement()).perform();
	}

	private void waitLoadAndVisible()
	{

		waitUntilDocumentStateIsComplete();

		waittUntilIsVisible();
	}

	private void waitUntilDocumentStateIsComplete()
	{
		wait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver)
			{
				if (driver instanceof JavascriptExecutor)
					((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");

				return Boolean.TRUE;
			}
		});
	}
}
