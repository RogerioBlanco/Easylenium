package org.easylenium.core.suitetest;

import org.apache.commons.lang3.StringUtils;
import org.easylenium.core.custom.CustomizerStep;
import org.easylenium.core.custom.CustomizerStepDefault;
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

public class Step
{

	private Table<String, String, TestCase<?>> table;

	private StepNode stepNode;

	private FactoryExecutor factory;

	private TestCase<?> testCase;

	private Class<? extends Executor> executorClass;

	private Executor executor;

	private CustomizerStep customStep;

	protected Step(Table<String, String, TestCase<?>> table, StepNode stepNode, FactoryExecutor factory)
	{
		this.factory = factory;
		this.table = table;
		this.stepNode = stepNode;
		this.executor = newExecutor();
		this.customStep = newCustomStep();
		validate();
	}

	@SuppressWarnings("unchecked")
	protected void validate()
	{
		if (!stepNode.isExecutor())
		{
			testCase = validateNameReference(stepNode.getName(), stepNode.getReference());
		} else
		{
			executorClass = (Class<? extends Executor>) validateExecutorClass(stepNode.getExecutor());
		}

	}

	private TestCase<?> validateNameReference(String name, String reference)
	{
		TestCase<?> testCase = table.get(name, reference);
		if (!StringUtils.isNoneBlank(name, reference) && testCase == null)
		{
			throw new ReferenceException("The step with name '%s' and reference '%s' doesn't exist.", stepNode.getName(), stepNode.getReference());
		}

		return testCase;
	}

	private Class<?> validateExecutorClass(String executorClass)
	{
		try
		{
			return Class.forName(executorClass);
		} catch (ClassNotFoundException e)
		{
			throw new RequirementException(e, "The class '%s' not exists or not find in the classpath of JVM.", executorClass);
		}
	}

	private Executor newExecutor()
	{
		if (!stepNode.isExecutor())
		{
			return factory.newExecutor(testCase.getExecutorClass(), testCase.getData());
		} else
		{
			return factory.newExecutor(executorClass, null);
		}
	}

	private CustomizerStep newCustomStep()
	{
		if (executor instanceof CustomizerStep)
			return (CustomizerStep) executor;

		return new CustomizerStepDefault();
	}

	protected void execute() throws ValidateTestCaseException, ExpectedException, TimeoutException, TimeoutWaitingException
	{
		customStep.before();
		
		executor.execute();

		executor.validate();
		
		customStep.after();
	}
}