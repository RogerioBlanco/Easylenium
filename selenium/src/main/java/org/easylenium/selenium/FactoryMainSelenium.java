package org.easylenium.selenium;

import junit.framework.TestSuite;

import org.easylenium.core.FactoryMainCore;
import org.easylenium.core.custom.CustomizerGeneral;
import org.easylenium.core.executor.FactoryExecutor;
import org.easylenium.selenium.custom.CustomizerGeneralSelenium;
import org.easylenium.selenium.executor.FactoryExecutorSeleniumDefault;
import org.easylenium.selenium.settings.SeleniumSettings;
import org.easylenium.selenium.util.Wrapper;
import org.easylenium.selenium.windows.DefaultFactoryWindowsManager;
import org.easylenium.selenium.windows.FactoryWindowsManager;
import org.easylenium.selenium.windows.WindowsBrowserManager;

public class FactoryMainSelenium
{

	public static TestSuite builder(SeleniumSettings settings) {
		return builder(settings, new DefaultFactoryWindowsManager(settings));	
	}
	
	public static TestSuite builder(SeleniumSettings settings, FactoryWindowsManager factory)
	{
		WindowsBrowserManager windowsManager = factory.newWindowsManager();

		Wrapper wrapper = new Wrapper(windowsManager, settings.getTimeout().getDefaultTimeout());
		
		return builder(settings, new FactoryExecutorSeleniumDefault(wrapper), new CustomizerGeneralSelenium(settings, windowsManager));
	}

	public static TestSuite builder(SeleniumSettings settings, FactoryExecutor factory, CustomizerGeneral custom)
	{
		return FactoryMainCore.builder(settings, factory, custom);
	}
}
