package org.easylenium.core.executor;

public abstract class ExecutorAbstract<T> implements Executor {

	public T data;

	public ExecutorAbstract(T data){
		this.data = data;
	}

}
