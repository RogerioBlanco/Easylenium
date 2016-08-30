package org.easylenium.core.testcase;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.easylenium.core.file.LoadRecursivelyFiles;
import org.easylenium.core.file.ParseFile;
import org.easylenium.core.file.exception.ParseFileToDocumentException;
import org.easylenium.core.xml.RootNode;
import org.xml.sax.SAXException;

public class TestCaseDataManager {

	private String path;
	
	public TestCaseDataManager(String path) {
		this.path = path;
	}

	public Map<String, Map<TestCaseData, TestCase>> setUpAllTestsCases() {
		Map<String, File> mapFiles = new LoadRecursivelyFiles(path).loadFiles();
		
		Map<String, Map<TestCaseData, TestCase>> map = new HashMap<String, Map<TestCaseData,TestCase>>();
		
		for(String key : mapFiles.keySet()){
			File file = mapFiles.get(key);
			try {
				TestCaseData testCaseData = new TestCaseData(key, new RootNode(new ParseFile(file).toDocument()));
				
				testCaseData.validate(file);
				
				map.putAll(testCaseData.getMapTestsCases());
			} catch (SAXException e) {
				throw new ParseFileToDocumentException(file, e);
			}
			
		}
		
		return map;
	}

}
