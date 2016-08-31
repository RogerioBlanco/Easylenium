package org.easylenium.core.testsuite;

import java.util.Collection;

import org.easylenium.core.testcase.TestCase;
import org.easylenium.core.util.Table;

import junit.framework.TestSuite;

public class TestSuitesManager {

	private String path;

	private Table<String, String, TestCase<?>> table;

	public TestSuitesManager(String path, Table<String, String, TestCase<?>> table) {
		this.path = path;
		this.table = table;
	}

	public Collection<TestSuite> createAllTestsSuites() {
		return null;
	}

}
