package org.test;

import org.easyleniu.selenium.page.Page;
import org.easylenium.core.testcase.executor.Executor;
import org.easylenium.core.testcase.executor.TimeoutWaitingException;
import org.easylenium.core.testcase.executor.exception.ExpectedException;
import org.easylenium.core.testcase.executor.exception.TimeoutException;
import org.easylenium.core.testcase.executor.exception.ValidateTestCaseException;

public class Teste extends Executor<DataTeste>{

	public Teste(Page page) {
		super(page);
	}

	public void execute(DataTeste data)
			throws ValidateTestCaseException, ExpectedException, TimeoutException, TimeoutWaitingException {
		page
	}

	public void validate()
			throws ValidateTestCaseException, ExpectedException, TimeoutException, TimeoutWaitingException {
		
	}

}
