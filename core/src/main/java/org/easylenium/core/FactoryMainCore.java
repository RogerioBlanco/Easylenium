package org.easylenium.core;

import java.util.Collection;

import junit.framework.TestSuite;

import org.easylenium.core.custom.CustomizerGeneral;
import org.easylenium.core.custom.CustomizerGeneralDefault;
import org.easylenium.core.executor.FactoryExecutor;
import org.easylenium.core.executor.FactoryExecutorDefault;
import org.easylenium.core.settings.Settings;
import org.easylenium.core.suitetest.TestSuitesManager;
import org.easylenium.core.testcase.TestCase;
import org.easylenium.core.testcase.TestCaseDataManager;
import org.easylenium.core.util.Table;

/**
 * 
 * @author rogerionunes
 *
 */
public class FactoryMainCore
{

	public static TestSuite builder(Settings settings)
	{
		return builder(settings, new FactoryExecutorDefault());
	}

	public static TestSuite builder(Settings settings, FactoryExecutor factory)
	{
		return builder(settings, factory, new CustomizerGeneralDefault());
	}

	public static TestSuite builder(Settings settings, FactoryExecutor factory, CustomizerGeneral custom)
	{

		if (settings == null)
			throw new IllegalArgumentException("The settings of the project not can be empty.");

		settings.validate();

		TestSuite mainTestSuite = new TestSuite(settings.getProjectName());

		Table<String, String, TestCase<?>> tableTestsCases = new TestCaseDataManager(settings.getTestsCasesDirectory()).setUpAllTestsCases();

		Collection<TestSuite> testsSuites = new TestSuitesManager(settings.getTestsSuitesDirectory(), tableTestsCases, factory).createAllTestsSuites(custom);

		for (TestSuite test : testsSuites)
			mainTestSuite.addTest(test);

		return mainTestSuite;
	}
}
