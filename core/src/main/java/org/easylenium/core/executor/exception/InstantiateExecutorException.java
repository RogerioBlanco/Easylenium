package org.easylenium.core.executor.exception;

public class InstantiateExecutorException extends RuntimeException
{

	private static final long serialVersionUID = -6602713307777870656L;

	public InstantiateExecutorException()
	{
		super();
	}

	public InstantiateExecutorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InstantiateExecutorException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public InstantiateExecutorException(String message)
	{
		super(message);
	}

	public InstantiateExecutorException(String message, Object... args)
	{
		this(String.format(message, args));
	}
	
	public InstantiateExecutorException(Throwable cause)
	{
		super(cause);
	}

}
