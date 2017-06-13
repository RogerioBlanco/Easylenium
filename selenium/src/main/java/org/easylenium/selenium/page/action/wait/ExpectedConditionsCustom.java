package org.easylenium.selenium.page.action.wait;

import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class ExpectedConditionsCustom
{

	public static ExpectedCondition<WebElement> invisibilityOfElementLocated(final By selector)
	{
		return new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver web)
			{
				List<WebElement> elements = web.findElements(selector);

				if (elements == null || elements.size() == 0)
					return null;

				for (WebElement element : elements)
					if (element.isDisplayed())
						return element;

				return null;
			}
		};
	}

	public static ExpectedCondition<Boolean> stateOfDocumentIsComplete()
	{
		return new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver web)
			{
				if (web instanceof JavascriptExecutor)
					((JavascriptExecutor) web).executeScript("return document.readyState").equals("complete");

				return Boolean.TRUE;
			}
		};
	}

	public static ExpectedCondition<WebElement> enabledOfElementLocated(final By selector)
	{
		return new ExpectedCondition<WebElement>() {

			public WebElement apply(WebDriver web)
			{
				List<WebElement> elements = web.findElements(selector);

				if (elements == null || elements.size() == 0)
					return null;

				for (WebElement element : elements)
					if (element.isEnabled())
						return element;

				return null;
			}
		};
	}

	public static ExpectedCondition<WebElement> textMatchWithElementText(final By selector, final String text)
	{
		return new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver web)
			{
				List<WebElement> elements = web.findElements(selector);

				if (elements == null || elements.size() == 0)
					return null;

				for (WebElement element : elements)
					if (element.getText().equalsIgnoreCase(text))
						return element;

				return null;
			}
		};
	}

	public static ExpectedCondition<WebElement> textContainsWithElementText(final By selector, final String text)
	{
		return new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver web)
			{
				List<WebElement> elements = web.findElements(selector);

				if (elements == null || elements.size() == 0)
					return null;

				for (WebElement element : elements)
					if (element.getText().contains(text))
						return element;

				return null;
			}
		};
	}
}
