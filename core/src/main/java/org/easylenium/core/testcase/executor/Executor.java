package org.easylenium.core.testcase.executor;

import org.easyleniu.selenium.page.Page;

public abstract class Executor<T> implements Execute<T>, ValidateTestCase{

	public Page page;

	public Executor(Page page){
		this.page = page;
	}

}
