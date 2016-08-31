package org.easyleniu.selenium.page;

import org.easyleniu.selenium.page.search.SearchIdElement;
import org.easyleniu.selenium.settings.SeleniumSettings;
import org.openqa.selenium.WebDriver;

public class Page{

	private WebDriver webDriver;
	
	private SeleniumSettings seleniumSettings;

	public Page(WebDriver webDriver, SeleniumSettings seleniumSettings) {
		this.webDriver = webDriver;
		this.seleniumSettings = seleniumSettings;
	}
	
	
	public SearchIdElement id(){
		return new SearchIdElement(this);
	}
}
