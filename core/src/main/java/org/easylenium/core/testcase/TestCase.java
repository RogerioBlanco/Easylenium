package org.easylenium.core.testcase;

import org.easylenium.core.testcase.xml.SimpleConvertXML;
import org.easylenium.core.testcase.xml.TestCaseNode;
import org.easylenium.core.util.Key;

public class TestCase<T>  {

	private String parentKey;

	private TestCaseNode node;

	private Class<?> executorClass;
	
	private T data;
	
	public TestCase(Class<?> executorClass, Class<?> dataClass, String parentKey, TestCaseNode node) {
		this.executorClass = executorClass;
		this.parentKey = parentKey;
		this.data = newData(dataClass, node);
	}

	public TestCase(Class<?> executorClass, Class<?> dataClass, String parentKey, TestCaseNode node, TestCase<T> template) {
		this(executorClass, dataClass, parentKey, node);
		this.data = newData(dataClass, node, template.data);
	}

	public Key<String, String> getKey() {
		return new Key<String, String>(parentKey, node.getId());
	}

	public void executeTestCase(){
		
	}
	
	@SuppressWarnings("unchecked")
	private T newData(Class<?> dataClass, TestCaseNode node){
		return new SimpleConvertXML().convert((Class<? extends T>) dataClass, node.toString());
	}
	
	@SuppressWarnings("unchecked")
	private T newData(Class<?> dataClass, TestCaseNode node, T template){
		return new SimpleConvertXML().convert((Class<? extends T>) dataClass, node.toString(), template);
	}
	
}
