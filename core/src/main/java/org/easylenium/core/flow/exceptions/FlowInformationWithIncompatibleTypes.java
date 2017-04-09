package org.easylenium.core.flow.exceptions;

public class FlowInformationWithIncompatibleTypes extends RuntimeException
{
	private static final long serialVersionUID = -5758720960636593487L;

	public FlowInformationWithIncompatibleTypes(String message)
	{
		super(message);
	}

	public FlowInformationWithIncompatibleTypes(String message, Object... args)
	{
		this(String.format(message, args));
	}
}
