package org.easylenium.core.suitetest.xml;

import java.io.File;
import java.util.Collection;

import org.easylenium.core.xml.RootNode;
import org.easylenium.core.xml.exception.TagUnequalsException;

public class SuiteTestRootNode {

	private static final String TAG_NAME = "SuiteTestCase";
	
	private static final String ATTR_NAME = "name";
	
	private static final String ATTR_DESC = "description";
	
	private static final String ATTR_ACTIVE = "active";
	
	private RootNode rootNode;

	public SuiteTestRootNode(RootNode rootNode) {
		this.rootNode = rootNode;
	}

	public void validate(File file) {
		if (!TAG_NAME.equalsIgnoreCase(rootNode.getElement().getTagName()))
			throw new TagUnequalsException("The root element in the file '%s' is not equals to '%s'.", file.getName(), TAG_NAME);
		
	}

	public String getName() {
		return rootNode.getAttribute(ATTR_NAME);
	}
	
	public String getDescription() {
		return rootNode.getAttribute(ATTR_DESC);
	}
	
	public Boolean isActive(){
		String value = rootNode.getAttribute(ATTR_ACTIVE);
		return Boolean.TRUE.toString().equalsIgnoreCase(value);
	}

	public Collection<SuitTestNode> getChildrenNodes() {
		// TODO Auto-generated method stub
		return null;
	}

}
