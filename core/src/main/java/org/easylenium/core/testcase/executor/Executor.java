package org.easylenium.core.testcase.executor;

import org.easylenium.core.testcase.executor.exception.ExpectedException;
import org.easylenium.core.testcase.executor.exception.TimeoutException;
import org.easylenium.core.testcase.executor.exception.ValidateTestCaseException;

public interface Executor<T> {

	public void execute(T data) throws ValidateTestCaseException, ExpectedException, TimeoutException, TimeoutWaitingException;

	public void validate(T data) throws ValidateTestCaseException, ExpectedException, TimeoutException, TimeoutWaitingException;
}
