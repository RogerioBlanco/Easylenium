package org.easylenium.core.testsuite;

import java.io.File;
import java.util.Collection;

import org.easylenium.core.file.LoadFiles;
import org.easylenium.core.file.ParseFile;
import org.easylenium.core.file.exception.ParseFileToDocumentException;
import org.easylenium.core.testcase.TestCase;
import org.easylenium.core.testcase.TestCaseData;
import org.easylenium.core.testcase.xml.TestCaseRootNode;
import org.easylenium.core.testsuite.xml.TestSuiteRootNode;
import org.easylenium.core.util.Table;
import org.easylenium.core.xml.RootNode;
import org.xml.sax.SAXException;

import junit.framework.TestSuite;

public class TestSuitesManager {

	private String path;

	private Table<String, String, TestCase<?>> table;

	public TestSuitesManager(String path, Table<String, String, TestCase<?>> table) {
		this.path = path;
		this.table = table;
	}

	public Collection<TestSuite> createAllTestsSuites() {
		Collection<File> files = new LoadFiles(path).loadRecursively();

		for(File file : files){
			try {
				TestSuiteRootNode testSuiteRootNode = new TestSuiteRootNode(new RootNode(new ParseFile(file).toDocument()));
			} catch (SAXException e) {
				throw new ParseFileToDocumentException(file, e);
			}
			
		}
		
		return null;
	}

}
