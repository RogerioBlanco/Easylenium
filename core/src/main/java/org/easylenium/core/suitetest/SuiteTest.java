package org.easylenium.core.suitetest;

import java.util.Collection;
import java.util.Collections;

import org.easylenium.core.suitetest.xml.ScenarioNode;
import org.easylenium.core.suitetest.xml.SuiteTestRootNode;
import org.easylenium.core.testcase.TestCase;
import org.easylenium.core.testcase.executor.FactoryExecutor;
import org.easylenium.core.util.Table;

import junit.framework.TestSuite;

public class SuiteTest {

	private SuiteTestRootNode rootNode;
	
	private Collection<Scenario> scenarios;

	public SuiteTest(SuiteTestRootNode rootNode, Table<String, String, TestCase<?>> table, FactoryExecutor factory) {
		this.rootNode = rootNode;
		this.scenarios = loadScenarios(table, factory);
	}

	public void validate() {
		rootNode.validate();
	}

	public TestSuite toTestSuite() {
		TestSuite testSuite = new TestSuite(rootNode.getName());
		
		for(Scenario scenario : scenarios)
			testSuite.addTest(scenario.toTest());
			
		return testSuite;
	}

	private Collection<Scenario> loadScenarios(Table<String, String, TestCase<?>> table, FactoryExecutor factory) {
		Collection<Scenario> scenarios = Collections.emptyList();
		
		for(ScenarioNode node : rootNode.getScenariosNodes()){
			if(node.isActive()){
				Scenario scenario = new Scenario(node, table, factory);
			
				scenarios.add(scenario);
			}
		}
		
		return scenarios;
	}
}
