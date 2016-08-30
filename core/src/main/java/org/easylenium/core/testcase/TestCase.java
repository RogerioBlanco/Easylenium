package org.easylenium.core.testcase;

import java.util.Collection;

import org.easylenium.core.interfaces.Validate;
import org.easylenium.core.testcase.xml.TestCaseNode;
import org.easylenium.core.util.Key;

public class TestCase implements Validate {

	private String parentKey;

	private TestCaseNode node;

	private Collection<TestCaseNode> templates;

	public TestCase(String parentKey, TestCaseNode node) {
		this.parentKey = parentKey;
	}

	public TestCase(String parentKey, TestCaseNode node, Collection<TestCaseNode> templates) {
		this(parentKey, node);
		this.templates = templates;
	}

	public Key<String, String> getKey() {
		return new Key<String, String>(parentKey, node.getId());
	}

	public void validate() {

	}

}
