package org.easylenium.core.executor;

import org.easylenium.core.executor.exception.ExpectedException;
import org.easylenium.core.executor.exception.TimeoutException;
import org.easylenium.core.executor.exception.TimeoutWaitingException;
import org.easylenium.core.executor.exception.ValidateTestCaseException;

public interface Executor {

	public void execute() throws ValidateTestCaseException, ExpectedException, TimeoutException, TimeoutWaitingException;

	public void validate() throws ValidateTestCaseException, ExpectedException, TimeoutException, TimeoutWaitingException;
}
