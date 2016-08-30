package org.easylenium.core.testcase;

import java.io.File;
import java.util.Collection;
import java.util.Collections;

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

	public Collection<TestCase> getTestsCases() {
		Collection<TestCaseNode> nodesWithReferences = Collections.emptyList();
		Collection<TestCaseNode> nodesOriginal = Collections.emptyList();
		
		rootNode.getOriginalNodes();
		rootNode.getReferenceNodes();
		for (TestCaseNode node : ) {
			node.validate();
			
			if(node.hasReference()){
				nodesWithReferences.add(node);
			} else {
				nodesOriginal.add(node);
			}
		}

		return null;
	}

	public String getId() {
		return FilenameUtils.removeExtension(file.getName());
	}
}
