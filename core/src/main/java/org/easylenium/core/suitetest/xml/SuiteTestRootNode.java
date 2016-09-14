package org.easylenium.core.suitetest.xml;

import java.io.File;

import org.easylenium.core.xml.RootNode;

public class SuiteTestRootNode {

	private static final String NAME = "NAME";
	private RootNode rootNode;

	public SuiteTestRootNode(RootNode rootNode) {
		this.rootNode = rootNode;
	}

	public void validate(File file) {
		// TODO Auto-generated method stub
		
	}

	public String getName() {
		return rootNode.getAttribute(NAME);
	}

}
