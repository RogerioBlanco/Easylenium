package org.easylenium.core.suitetest;

import java.io.File;
import java.util.Collection;
import java.util.Collections;

import org.easylenium.core.executor.FactoryExecutor;
import org.easylenium.core.file.LoadFiles;
import org.easylenium.core.file.ParseFile;
import org.easylenium.core.file.exception.ParseFileToDocumentException;
import org.easylenium.core.suitetest.xml.SuiteTestRootNode;
import org.easylenium.core.testcase.TestCase;
import org.easylenium.core.util.Table;
import org.easylenium.core.xml.RootNode;
import org.xml.sax.SAXException;

import junit.framework.TestSuite;

public class TestSuitesManager {

	private String path;

	private Table<String, String, TestCase<?>> table;

	private FactoryExecutor factory;

	public TestSuitesManager(String path, Table<String, String, TestCase<?>> table, FactoryExecutor factory) {
		this.path = path;
		this.table = table;
		this.factory = factory;
	}

	public Collection<TestSuite> createAllTestsSuites() {
		Collection<TestSuite> testsSuites = Collections.emptyList();
		Collection<File> files = new LoadFiles(path).loadRecursively();
		
		for(File file : files){
			try {
				SuiteTestRootNode rootNode = new SuiteTestRootNode(file, new RootNode(new ParseFile(file).toDocument()));
				
				if(rootNode.isActive()) {
					SuiteTest suiteTest = new SuiteTest(rootNode, table, factory);
					
					suiteTest.validate();
					
					testsSuites.add(suiteTest.toTestSuite());
				}
			} catch (SAXException e) {
				throw new ParseFileToDocumentException(file, e);
			}
		}
		
		return testsSuites;
	}

}
