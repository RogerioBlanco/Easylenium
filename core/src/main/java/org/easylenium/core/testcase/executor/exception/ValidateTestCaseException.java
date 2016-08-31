package org.easylenium.core.testcase.executor.exception;

public class ValidateTestCaseException extends RuntimeException {

	private static final long serialVersionUID = 6896598220524738878L;

	public ValidateTestCaseException() {
		super();
	}

	public ValidateTestCaseException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public ValidateTestCaseException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ValidateTestCaseException(String arg0) {
		super(arg0);
	}

	public ValidateTestCaseException(Throwable arg0) {
		super(arg0);
	}

}
