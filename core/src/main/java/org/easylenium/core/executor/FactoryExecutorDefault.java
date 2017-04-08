package org.easylenium.core.executor;

import org.easylenium.core.executor.exception.InstantiateExecutorException;


public class FactoryExecutorDefault implements FactoryExecutor
{

	public <T> Executor newExecutor(Class<? extends Executor> executorClass, T data)
	{
		try
		{
			if (data != null)
				return executorClass.getConstructor(data.getClass()).newInstance(data);

			return executorClass.newInstance();
		} catch (Exception e)
		{
			throw new InstantiateExecutorException("It isn't possible instantiate an object of class '%s'.", executorClass.getName());
		}

	}

}
