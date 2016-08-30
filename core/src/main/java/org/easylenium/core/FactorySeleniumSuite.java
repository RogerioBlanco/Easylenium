package org.easylenium.core;

import java.util.Collection;
import java.util.Map;

import org.easylenium.core.settings.Settings;
import org.easylenium.core.testcase.TestCase;
import org.easylenium.core.testcase.TestCaseData;
import org.easylenium.core.testcase.TestCaseDataManager;
import org.easylenium.core.testsuite.TestSuitesManager;

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

		TestSuite mainTestSuite = new TestSuite(settings.getProjectName());

		Map<String, TestCase> testsCases = new TestCaseDataManager(settings.getPathTestsCases()).setUpAllTestsCases();
		
		Collection<TestSuite> testsSuites = new TestSuitesManager(settings.getPathTestsSuites(), testsCases).createAllTestsSuites();

		for (TestSuite test : testsSuites) {
			mainTestSuite.addTest(test);
		}

		return mainTestSuite;
	}
}
