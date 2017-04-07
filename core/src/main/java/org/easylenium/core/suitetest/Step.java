package org.easylenium.core.suitetest;

import org.apache.commons.lang3.StringUtils;
import org.easylenium.core.executor.Executor;
import org.easylenium.core.executor.FactoryExecutor;
import org.easylenium.core.executor.exception.ExpectedException;
import org.easylenium.core.executor.exception.TimeoutException;
import org.easylenium.core.executor.exception.TimeoutWaitingException;
import org.easylenium.core.executor.exception.ValidateTestCaseException;
import org.easylenium.core.suitetest.exception.ReferenceException;
import org.easylenium.core.suitetest.xml.StepNode;
import org.easylenium.core.testcase.TestCase;
import org.easylenium.core.util.Table;
import org.easylenium.core.xml.exception.RequirementException;

public class Step {

	private Table<String, String, TestCase<?>> table;
	
	private StepNode stepNode;

	private FactoryExecutor factory;

	private TestCase<?> testCase;

	private Class<? extends Executor> executorClass;

	public Step(Table<String, String, TestCase<?>> table, StepNode stepNode, FactoryExecutor factory) {
		this.factory = factory;
		this.table = table;
		this.stepNode = stepNode;
		validate();
	}

	@SuppressWarnings("unchecked")
	public void validate() {
		if(!stepNode.isExecutor()) {
			testCase = validateNameReference(stepNode.getName(), stepNode.getReference());
		} else {
			executorClass = (Class<? extends Executor>) validateExecutorClass(stepNode.getExecutor());
		}
			
	}

	private TestCase<?> validateNameReference(String name, String reference) {
		TestCase<?> testCase = table.get(name, reference);
		if (!StringUtils.isNoneBlank(name, reference) && testCase == null){
			throw new ReferenceException("The step with name '%s' and reference '%s' doesn't exist.", stepNode.getName(), stepNode.getReference());
		}
		
		return testCase;
	}

	private Class<?> validateExecutorClass(String executorClass){
		try {
			return Class.forName(executorClass);
		} catch (ClassNotFoundException e) {
			throw new RequirementException(e, "The class '%s' not exists or not find in the classpath of JVM.", executorClass);
		}		
	}

	public void execute() throws ValidateTestCaseException, ExpectedException, TimeoutException, TimeoutWaitingException{
		Executor executor = null;
		
		if(!stepNode.isExecutor()) {
			executor = factory.newExecutor(testCase.getExecutorClass(), testCase.getData());
		} else {
			executor = factory.newExecutor(executorClass, null);
		}
		
		executor.execute();
		
		executor.validate();
	}
	
}
