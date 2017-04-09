package org.easylenium.core.suitetest;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;

import org.apache.commons.lang3.StringUtils;
import org.easylenium.core.constant.Result;
import org.easylenium.core.custom.CustomizerGeneral;
import org.easylenium.core.executor.FactoryExecutor;
import org.easylenium.core.executor.exception.ExpectedException;
import org.easylenium.core.flow.InformationFlow;
import org.easylenium.core.suitetest.xml.ScenarioNode;
import org.easylenium.core.suitetest.xml.StepNode;
import org.easylenium.core.testcase.TestCase;
import org.easylenium.core.util.Table;

public class Scenario
{

	private ScenarioNode node;

	private Table<String, String, TestCase<?>> table;

	private List<Step> steps;

	private FactoryExecutor factory;

	protected Scenario(ScenarioNode rootNode, Table<String, String, TestCase<?>> table, FactoryExecutor factory)
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

	protected void validate()
	{
		for (Step step : steps)
			step.validate();
	}

	protected Test toTest(CustomizerGeneral custom)
	{
		return new ScenarioTestCase(node.getName(), node.getDescription(), this, custom);
	}

	public String getName()
	{
		return node.getName();
	}

	public String getDescription()
	{
		return node.getDescription();
	}

	private static class ScenarioTestCase extends junit.framework.TestCase
	{
		private Scenario scenario;

		private CustomizerGeneral defaultCustom;

		public ScenarioTestCase(String name, String description, Scenario scenario, CustomizerGeneral custom)
		{
			this.scenario = scenario;
			this.defaultCustom = custom;
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
				InformationFlow informationFlow = new InformationFlow(scenario);

				defaultCustom.before(scenario);

				for (Step step : scenario.steps)
				{
					defaultCustom.before(step);
					
					step.execute(informationFlow);

					defaultCustom.after(step);
				}

				defaultCustom.after(scenario);

			} catch (ExpectedException expectedException)
			{
				result = Result.SUCCESS;

				defaultCustom.expectedException(scenario);
			} catch (Throwable throwable)
			{
				result = Result.FAIL;

				throw throwable;
			} finally
			{
				defaultCustom.result(result);
			}
		}
	}

}
