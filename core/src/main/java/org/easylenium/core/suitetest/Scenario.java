package org.easylenium.core.suitetest;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.easylenium.core.constant.Result;
import org.easylenium.core.custom.Customizer;
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
		List<Step> steps = new ArrayList<Step>();

		for (StepNode stepNode : node.getStepsNodes())
			steps.add(new Step(table, stepNode, factory));

		return steps;
	}

	public void validate()
	{
		for (Step step : steps)
			step.validate();
	}

	public Test toTest(Customizer custom)
	{
		return new ScenarioTestCase(node.getName(), node.getDescription(), this, custom);
	}

	private static class ScenarioTestCase extends junit.framework.TestCase
	{


		private Scenario scenario;
		private Customizer custom;

		public ScenarioTestCase(String name, String description, Scenario scenario, Customizer custom)
		{
			this.scenario = scenario;
			this.custom = custom;
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
			Result result = Result.SUCCESS;
			
			try
			{
				custom.before(scenario);
				
				for (Step step : scenario.steps) {
					custom.before(step);
					
					step.execute();
					
					custom.after(step);
				}

				custom.after(scenario);
				
			} catch (ExpectedException expectedException)
			{
				result = Result.SUCCESS;
				
				custom.expectedException(scenario);
			} catch (Throwable throwable)
			{
				result = Result.FAIL;
				
				throw throwable;
			} finally
			{
				custom.result(result);
			}
		}
	}

}
