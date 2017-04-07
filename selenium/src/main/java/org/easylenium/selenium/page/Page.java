package org.easylenium.selenium.page;

import org.easylenium.selenium.page.search.SearchIdElement;
import org.easylenium.selenium.settings.SeleniumSettings;
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
