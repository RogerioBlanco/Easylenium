package org.easylenium.core.testcase;

import java.io.File;
import java.util.Collection;

import org.easylenium.core.file.LoadFiles;
import org.easylenium.core.file.ParseFile;
import org.easylenium.core.file.exception.ParseFileToDocumentException;
import org.easylenium.core.testcase.xml.TestCaseRootNode;
import org.easylenium.core.util.Table;
import org.easylenium.core.xml.RootNode;
import org.xml.sax.SAXException;

public class TestCaseDataManager {

	private String path;
	
	public TestCaseDataManager(String path) {
		this.path = path;
	}

	public Table<String, String, TestCase> setUpAllTestsCases() {
		Table<String, String, TestCase> table = new Table<String, String, TestCase>();

		Collection<File> files = new LoadFiles(path).loadRecursively();

		for(File file : files){
			try {
				TestCaseRootNode testCaseRootNode = new TestCaseRootNode(new RootNode(new ParseFile(file).toDocument()));
				
				TestCaseData testCaseData = new TestCaseData(file, testCaseRootNode);
				
				testCaseData.validate();
				
				for(TestCase testCase : testCaseData.getTestsCases())
					table.put(testCase.getKey(), testCase);
				
			} catch (SAXException e) {
				throw new ParseFileToDocumentException(file, e);
			}
			
		}
		
		return table;
	}

}
