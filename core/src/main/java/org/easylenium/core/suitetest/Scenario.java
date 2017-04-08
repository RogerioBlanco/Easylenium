package org.easylenium.core.suitetest;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.easylenium.core.executor.FactoryExecutor;
import org.easylenium.core.executor.exception.ExpectedException;
import org.easylenium.core.suitetest.xml.ScenarioNode;
import org.easylenium.core.suitetest.xml.StepNode;
import org.easylenium.core.testcase.TestCase;
import org.easylenium.core.util.Table;

import junit.framework.Test;

public class Scenario
{

	private ScenarioNode node;

	private Table<String, String, TestCase<?>> table;

	private List<Step> steps;

	private FactoryExecutor factory;

	public Scenario(ScenarioNode rootNode, Table<String, String, TestCase<?>> table, FactoryExecutor factory)
	{
		this.node = rootNode;
		this.table = table;
		this.factory = factory;
		this.steps = loadSteps();
	}

	private List<Step> loadSteps()
	{
		List<Step> steps = new ArrayList();

		for (StepNode stepNode : node.getStepsNodes())
			steps.add(new Step(table, stepNode, factory));

		return steps;
	}

	public void validate()
	{
		for (Step step : steps)
			step.validate();
	}

	public Test toTest()
	{
		return new ScenarioTestCase(node.getName(), node.getDescription(), steps);
	}

	private static class ScenarioTestCase extends junit.framework.TestCase
	{

		private List<Step> steps;

		public ScenarioTestCase(String name, String description, List<Step> steps)
		{
			this.steps = steps;
			setName(name, description);
		}

		private void setName(String name, String description)
		{
			if (StringUtils.isNotBlank(description))
				name = name.concat(" - ").concat(description);
			setName(name);
		}

		protected void runTest() throws Throwable
		{
			try
			{
				for (Step step : steps)
				{
					step.execute();
				}
			} catch (ExpectedException expectedException)
			{

			} catch (Throwable throwable)
			{
				throw throwable;
			}
		}
	}

}
