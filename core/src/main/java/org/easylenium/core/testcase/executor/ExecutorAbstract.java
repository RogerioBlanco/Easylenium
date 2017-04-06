package org.easylenium.core.testcase.executor;

public abstract class ExecutorAbstract<T> implements Executor {

	public T data;

	private ExecutorAbstract(T data){
		this.data = data;
	}

}
