package org.easylenium.core.testcase;

import org.easyleniu.selenium.page.Page;
import org.easylenium.core.testcase.executor.Executor;
import org.easylenium.core.testcase.executor.exception.InstantiateExecutorException;
import org.easylenium.core.testcase.xml.TestCaseNode;
import org.easylenium.core.util.Key;
import org.easylenium.core.xml.util.SimpleConvertXML;

public class TestCase<T>  {

	private String parentKey;

	private TestCaseNode node;

	private Class<? extends Executor<T>> executorClass;
	
	private T data;
	
	public TestCase(Class<? extends Executor<T>> executorClass, Class<?> dataClass, String parentKey, TestCaseNode node) {
		this.executorClass = executorClass;
		this.parentKey = parentKey;
		this.data = newData(dataClass, node);
	}

	public TestCase(Class<? extends Executor<T>> executorClass, Class<?> dataClass, String parentKey, TestCaseNode node, TestCase<T> template) {
		this(executorClass, dataClass, parentKey, node);
		this.data = newData(dataClass, node, template.data);
	}

	public Key<String, String> getKey() {
		return new Key<String, String>(parentKey, node.getId());
	}

	@SuppressWarnings("unchecked")
	public void executeExecutor(Page page){
		Executor<T> executor = (Executor<T>) newExecutor(executorClass, page);
		
		executor.execute(data);
		
		executor.validate();
	}
	
	private Executor<?> newExecutor(Class<? extends Executor<T>> executorClass, Page page) {
		try {
			return executorClass.getDeclaredConstructor(Page.class).newInstance(page);
		} catch (Exception e) {
			throw new InstantiateExecutorException("It was not possible instantiate an object of class " + executorClass.getName());
		}
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
