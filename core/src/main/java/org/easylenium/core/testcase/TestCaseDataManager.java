package org.easylenium.core.testcase;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TestCaseDataManager {

	private String path;

	private Map<String, File> map;

	public TestCaseDataManager(String path) {
		this.path = path;
	}

	public Collection<TestCaseData> setUpAllTestsCasesDatas() {
		this.map = loadFiles(new File(path));

		return null;
	}

	private Map<String, File> loadFiles(File directory) {
		HashMap<String, File> map = new HashMap<String, File>();
		
		for (File file : directory.listFiles()) {
			if(file.isDirectory()) {
				map.putAll(loadFiles(file));
			} else {
//				map.put(key, value);
			}
		}
		
		return map;
	}


}
