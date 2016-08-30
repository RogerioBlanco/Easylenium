package org.easylenium.core.testcase.xml;

import org.apache.commons.lang3.StringUtils;
import org.easylenium.core.interfaces.Validate;
import org.easylenium.core.xml.NodeElement;
import org.easylenium.core.xml.exception.RequirementException;
import org.easylenium.core.xml.exception.TagUnequalsException;

public class TestCaseNode implements Validate {

	private static final String TAG_NAME = "TestCase";

	private static final String ATTR_ID = "id";

	private static final String ATTR_REF = "ref";

	private NodeElement node;

	public TestCaseNode(NodeElement node) {
		this.node = node;
	}

	public void validate() {
		if (!TAG_NAME.equalsIgnoreCase(node.getTagName()))
			throw new TagUnequalsException(TAG_NAME);
		
		if(StringUtils.isEmpty(getId()))
			throw new RequirementException("Exists an element that does not have an id.");
		
	}

	public String getId() {
		return node.getAttribute(ATTR_ID);
	}

	public String getReference(){
		return node.getAttribute(ATTR_REF);
	}
	
	public boolean hasReference() {
		return StringUtils.isNotEmpty(getReference());
	}

}
