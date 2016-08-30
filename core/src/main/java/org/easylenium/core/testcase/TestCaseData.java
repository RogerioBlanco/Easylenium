package org.easylenium.core.testcase;

import java.io.File;
import java.util.Map;

import org.easylenium.core.xml.NodeElement;
import org.easylenium.core.xml.RootNode;
import org.easylenium.core.xml.exception.TagUnequalsException;

public class TestCaseData {

	private static final String TAG_NAME = "TestCaseData";

	private static final String ATTR_EXECUTOR_CLASS = "Executor";

	private static final String ATTR_DATA_CLASS = "Data";

	private String key;

	private RootNode rootNode;

	private String executorClassName;

	private String dataClassName;

	public TestCaseData(String key, RootNode rootNode) {
		this.key = key;
		this.rootNode = rootNode;

		executorClassName = rootNode.getAttribute(ATTR_EXECUTOR_CLASS);

		dataClassName = rootNode.getAttribute(ATTR_DATA_CLASS);
	}

	public void validate(File file) {
		if (!TAG_NAME.equalsIgnoreCase(rootNode.getElement().getTagName()))
			throw new TagUnequalsException(TAG_NAME, file);
		
		validateClassName(executorClassName);

		validateClassName(dataClassName);
	}
	
	private void validateClassName(String className){
		try {
			Class.forName(dataClassName);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(String.format("The class '%s' not exists or not find in the classpath.", className), e);
		}		
	}

	public Map<String, TestCase> getMapTestsCases() {
		
		for (NodeElement node : rootNode.getChildrenNodes()) {
			TestCaseNode testCase = new TestCaseNode(node);
			
		}

		return null;
	}

}
