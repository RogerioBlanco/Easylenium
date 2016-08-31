package org.easylenium.core.testcase;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.easylenium.core.interfaces.Validate;
import org.easylenium.core.testcase.xml.TestCaseNode;
import org.easylenium.core.testcase.xml.TestCaseRootNode;
import org.easylenium.core.xml.exception.RequirementException;

public class TestCaseData implements Validate{

	private TestCaseRootNode rootNode;

	private File file;

	private String executorClassName;

	private String dataClassName;

	public TestCaseData(File file, TestCaseRootNode rootNode) {
		this.file = file;
		
		this.rootNode = rootNode;

		executorClassName = rootNode.getExecutorClass();

		dataClassName = rootNode.getDataClass();
	}

	public void validate() {
		rootNode.validate(file);
		
		validateClassName(executorClassName);

		validateClassName(dataClassName);
	}
	
	private void validateClassName(String className){
		try {
			Class.forName(dataClassName);
		} catch (ClassNotFoundException e) {
			throw new RequirementException(e, "The class '%s' not exists or not find in the classpath.", className);
		}		
	}

	public Collection<TestCase<?>> getTestsCases() {
		Collection<TestCase<?>> cases = Collections.emptyList();
		Map<String, TestCase<?>> templates = new HashMap<String, TestCase<?>>();
		
		for (TestCaseNode node : rootNode.getChildrenNodesWithoutAttributeTemplate()) {
			@SuppressWarnings("unchecked")
			TestCase<?> testCase = new TestCase(executorClass(), dataClass(), getId(), node);
			
			cases.add(testCase);
			
			templates.put(node.getId(), testCase);
		}
		
		for(TestCaseNode node: rootNode.getChildrenNodesWithoutAttributeTemplate()){
			@SuppressWarnings("unchecked")
			TestCase<?> testCase = new TestCase(executorClass(), dataClass(), getId(), node, templates.get(node.getId()));
			
			cases.add(testCase);
		}
		
		return cases;
	}


	private Class<?> executorClass() {
		validateClassName(executorClassName);
		try {
			return Class.forName(executorClassName);
		} catch (ClassNotFoundException e) { 
			throw new RuntimeException(e);/*ignored because has been validated*/
		}
	}
	
	private Class<?> dataClass() {
		validateClassName(dataClassName);
		
		try {
			return Class.forName(dataClassName);
		} catch (ClassNotFoundException e) { 
			throw new RuntimeException(e);/*ignored because has been validated*/
		}
	}

	public String getId() {
		return FilenameUtils.removeExtension(file.getName());
	}
}
