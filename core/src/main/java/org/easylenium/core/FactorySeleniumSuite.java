package org.easylenium.core;

import org.easylenium.core.settings.Settings;

import junit.framework.TestSuite;

/**
 * 
 * @author rogerionunes
 *
 */
public class FactorySeleniumSuite {

	public static TestSuite builder(Settings settings) {
		
		if (settings == null)
			throw new IllegalArgumentException("The settings of the project not can be empty.");

		settings.validate();

		TestSuite mainSuite = new TestSuite(settings.getProjectName());

		return mainSuite;
	}
}
