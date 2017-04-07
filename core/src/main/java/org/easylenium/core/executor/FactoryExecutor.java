package org.easylenium.core.executor;

public interface FactoryExecutor {

	public abstract <T> Executor newExecutor(Class<? extends Executor> executorClass, T object );

}
