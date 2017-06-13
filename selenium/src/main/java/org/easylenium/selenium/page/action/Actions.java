package org.easylenium.selenium.page.action;

import java.util.List;

import org.easylenium.selenium.page.action.wait.ExpectedConditionsCustom;
import org.easylenium.selenium.page.exception.ElementNotFoundException;
import org.easylenium.selenium.settings.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Actions
{
	private By selector;

	private WebDriver webDriver;

	private WebDriverWait wait;

	public Actions(WebDriver current, Timeout timeout, By selector)
	{
		this.webDriver = current;
		this.selector = selector;
		this.wait = new WebDriverWait(current, timeout.getTimeUnit().toSeconds(timeout.getTime()));
	}

	public WebElement findElement()
	{
		return findElement(Boolean.TRUE, Boolean.TRUE);
	}

	public WebElement findElement(boolean needToBeDisplayed, boolean needToBeEnabled)
	{
		for (WebElement element : findElements(needToBeDisplayed, needToBeEnabled))
			if ((!needToBeDisplayed || (needToBeDisplayed && element.isDisplayed())) && (!needToBeEnabled || (needToBeEnabled && element.isEnabled())))
				return element;

		throw new ElementNotFoundException("The element is not found.");
	}

	public List<WebElement> findElements()
	{
		return findElements(Boolean.TRUE, Boolean.TRUE);
	}

	public List<WebElement> findElements(boolean needToBeDisplayed, boolean needToBeEnabled)
	{
		wait.until(ExpectedConditionsCustom.stateOfDocumentIsComplete());

		if (needToBeDisplayed)
			waittUntilIsVisible();

		if (needToBeEnabled)
			waitUntilIsEnabled();

		return webDriver.findElements(selector);
	}

	public Actions click()
	{

		findElement().click();

		return this;
	}

	public Actions fill(CharSequence... text)
	{
		findElement().sendKeys(text);
		return this;
	}

	public Actions clear()
	{
		findElement().clear();
		return this;
	}

	public Select select()
	{
		return new Select(findElement());
	}
	
	public boolean existElement()
	{
		try
		{
			wait.until(ExpectedConditionsCustom.stateOfDocumentIsComplete());

			return !webDriver.findElements(selector).isEmpty();

		} catch (Exception e)
		{
			return Boolean.FALSE;
		}
	}
	
	public WebElement waitUntilTextMatch(String text){
		return wait.until(ExpectedConditionsCustom.textMatchWithElementText(selector, text));
	}
	
	public WebElement waitUntilTextContains(String text){
		return wait.until(ExpectedConditionsCustom.textContainsWithElementText(selector, text));
	}
	
	public WebElement waittUntilIsVisible()
	{
		return wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
	}

	public WebElement waitUntilIsEnabled()
	{
		return wait.until(ExpectedConditionsCustom.enabledOfElementLocated(selector));
	}

	public WebElement waitUntilHide()
	{
		return wait.until(ExpectedConditionsCustom.invisibilityOfElementLocated(selector));
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

	public void focus()
	{
		new org.openqa.selenium.interactions.Actions(webDriver).moveToElement(findElement()).perform();
	}

}
