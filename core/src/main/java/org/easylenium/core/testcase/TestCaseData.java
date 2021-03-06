package org.easylenium.core.testcase;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.easylenium.core.executor.Executor;
import org.easylenium.core.testcase.xml.TestCaseNode;
import org.easylenium.core.testcase.xml.TestCaseRootNode;
import org.easylenium.core.xml.exception.RequirementException;

public class TestCaseData
{

	private TestCaseRootNode rootNode;

	private File file;

	private String executorClassName;

	private String dataClassName;

	public TestCaseData(File file, TestCaseRootNode rootNode)
	{
		this.file = file;

		this.rootNode = rootNode;

		this.executorClassName = rootNode.getExecutorClass();

		this.dataClassName = rootNode.getDataClass();
	}

	public void validate()
	{
		rootNode.validate(file);

		validateClassName(executorClassName);

		validateClassName(dataClassName);
	}

	private void validateClassName(String className)
	{
		try
		{
			Class.forName(className);
		} catch (ClassNotFoundException e)
		{
			throw new RequirementException(e, "The class '%s' not exists or not find in the classpath of JVM.", className);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Collection<TestCase<?>> getTestsCases()
	{
		Collection<TestCase<?>> cases = new ArrayList();
		Map<String, TestCase<?>> templates = new HashMap<String, TestCase<?>>();

		for (TestCaseNode node : rootNode.getChildrenNodesWithoutAttributeTemplate())
		{
			TestCase<?> testCase = new TestCase(executorClass(), dataClass(), getId(), node);

			cases.add(testCase);

			templates.put(node.getId(), testCase);
		}

		for (TestCaseNode node : rootNode.getChildrenNodesWithAttributeTemplate())
		{
			TestCase<?> testCase = new TestCase(executorClass(), dataClass(), getId(), node, templates.get(node.getId()));

			cases.add(testCase);
		}

		return cases;
	}

	private Class<? extends Executor> executorClass()
	{
		validateClassName(executorClassName);

		try
		{
			Class<?> clazz = Class.forName(executorClassName);
			
			if(!Executor.class.isAssignableFrom(clazz))
				throw new RequirementException("The class '%s' doesn't implements the interface 'org.easylenium.core.executor.Executor'.", executorClassName);
			
			return (Class<? extends Executor>) clazz;
		} catch (ClassNotFoundException e)
		{
			throw new RuntimeException(e);/* ignored because has been validated */
		}
	}

	private Class<?> dataClass()
	{
		validateClassName(dataClassName);

		try
		{
			return Class.forName(dataClassName);
		} catch (ClassNotFoundException e)
		{
			throw new RuntimeException(e);/* ignored because has been validated */
		}
	}

	public String getId()
	{
		return FilenameUtils.removeExtension(file.getName());
	}
}
