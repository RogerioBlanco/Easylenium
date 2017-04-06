package org.easylenium.core.testcase.executor;

public interface FactoryExecutor {

	public <T> Executor newExecutor(Class<? extends Executor> executorClass, T data );

}
