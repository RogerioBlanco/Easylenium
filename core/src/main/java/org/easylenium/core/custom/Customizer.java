package org.easylenium.core.custom;

import org.easylenium.core.constant.Result;
import org.easylenium.core.suitetest.Scenario;
import org.easylenium.core.suitetest.Step;

public interface Customizer
{
	public void before(Scenario scenario);

	public void after(Scenario scenario);
	
	public void before(Step scenario);

	public void after(Step scenario);

	public void expectedException(Scenario scenario);
	
	public void result(Result result);
}
