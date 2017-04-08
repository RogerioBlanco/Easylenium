package org.easylenium.core.executor;

public abstract class ExecutorData<T> implements Executor
{

	public T data;

	public ExecutorData(T data)
	{
		this.data = data;
	}

}
