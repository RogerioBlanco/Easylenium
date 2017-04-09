package org.easylenium.selenium.page;

import org.easylenium.selenium.util.Wrapper;

public abstract class PageObject extends PageObjectData<Object>
{

	public PageObject(Wrapper wrapper)
	{
		super(wrapper, null);
	}
}
