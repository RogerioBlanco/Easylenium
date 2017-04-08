package org.easylenium.core.suitetest;

import java.util.ArrayList;
import java.util.Collection;

import org.easylenium.core.custom.Customizer;
import org.easylenium.core.executor.FactoryExecutor;
import org.easylenium.core.suitetest.xml.ScenarioNode;
import org.easylenium.core.suitetest.xml.SuiteTestRootNode;
import org.easylenium.core.testcase.TestCase;
import org.easylenium.core.util.Table;

import junit.framework.TestSuite;

public class SuiteTest
{

	private SuiteTestRootNode rootNode;

	private Collection<Scenario> scenarios;

	public SuiteTest(SuiteTestRootNode rootNode, Table<String, String, TestCase<?>> table, FactoryExecutor factory)
	{
		this.rootNode = rootNode;
		this.scenarios = loadScenarios(table, factory);
	}

	public void validate()
	{
		rootNode.validate();
	}

	public TestSuite toTestSuite(Customizer custom)
	{
		TestSuite testSuite = new TestSuite(rootNode.getName());

		for (Scenario scenario : scenarios)
			testSuite.addTest(scenario.toTest(custom));

		return testSuite;
	}

	private Collection<Scenario> loadScenarios(Table<String, String, TestCase<?>> table, FactoryExecutor factory)
	{
		Collection<Scenario> scenarios = new ArrayList<Scenario>();

		for (ScenarioNode node : rootNode.getScenariosNodes())
		{
			if (node.isActive())
			{
				Scenario scenario = new Scenario(node, table, factory);

				scenarios.add(scenario);
			}
		}

		return scenarios;
	}
}
