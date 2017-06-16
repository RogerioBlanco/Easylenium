package org.selenium.example.demoqa;

import org.easylenium.selenium.FactoryMainSelenium;
import org.easylenium.selenium.settings.Browser;
import org.easylenium.selenium.settings.SeleniumSettings;
import org.easylenium.selenium.settings.TimeoutSeleniumWrapper;

import junit.framework.Test;

public class MainExample {

	public static Test suite() {
		SeleniumSettings settings = new SeleniumSettings(Browser.FIREFOX);
		
		settings.setProjectName("DemoQA - Example - Test");
		
		settings.setTimeoutManager(TimeoutSeleniumWrapper.DEFAULT);
		
		settings.setPathTestsCases("./testCases/");
		
		settings.setPathTestsSuites("./testSuites/");
		
		return FactoryMainSelenium.builder(settings);
	}
}
