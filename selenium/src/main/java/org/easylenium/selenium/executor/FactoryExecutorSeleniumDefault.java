package org.easylenium.selenium.executor;

import org.easylenium.core.executor.Executor;
import org.easylenium.core.executor.FactoryExecutor;
import org.easylenium.core.executor.FactoryExecutorDefault;
import org.easylenium.core.executor.exception.InstantiateExecutorException;
import org.easylenium.selenium.page.PageObject;
import org.easylenium.selenium.page.PageObjectData;
import org.easylenium.selenium.util.Wrapper;

public class FactoryExecutorSeleniumDefault implements FactoryExecutor
{

	private Wrapper wrapper;

	public FactoryExecutorSeleniumDefault(Wrapper wrapper)
	{
		this.wrapper = wrapper;
	}

	public <T> Executor newExecutor(Class<? extends Executor> executorClass, T data)
	{
		try
		{
			if (PageObject.class.isAssignableFrom(executorClass))
				return executorClass.getConstructor(Wrapper.class).newInstance(wrapper);

			if (data != null && PageObjectData.class.isAssignableFrom(executorClass))
				return executorClass.getConstructor(Wrapper.class, data.getClass()).newInstance(wrapper, data);

			return new FactoryExecutorDefault().newExecutor(executorClass, data);
		} catch (Exception e)
		{
			throw new InstantiateExecutorException("It isn't possible instantiate an object of class '%s'.", executorClass.getName());
		}
	}

}
