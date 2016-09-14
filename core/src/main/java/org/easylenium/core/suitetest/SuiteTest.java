package org.easylenium.core.suitetest;

import java.io.File;
import java.util.Collection;

import org.easylenium.core.suitetest.xml.SuiteTestRootNode;
import org.easylenium.core.testcase.TestCase;
import org.easylenium.core.util.Table;

import junit.framework.TestSuite;

public class SuiteTest {

	private SuiteTestRootNode rootNode;
	
	private Table<String, String, TestCase<?>> table;

	public SuiteTest(SuiteTestRootNode rootNode, Table<String, String, TestCase<?>> table) {
		this.rootNode = rootNode;
		this.table = table;
	}

	public void validate(File file) {
		rootNode.validate(file);
		
	}

	public TestSuite toTestSuite() {
		TestSuite testSuite = new TestSuite(rootNode.getName());
		
		for(Scenario scenario : getScenarios())
			testSuite.addTest(scenario.toTest());
			
		return testSuite;
	}

	private Collection<Scenario> getScenarios() {
		return null;
	}


}
