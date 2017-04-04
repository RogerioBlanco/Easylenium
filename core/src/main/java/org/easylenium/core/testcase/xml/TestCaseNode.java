package org.easylenium.core.testcase.xml;

import org.apache.commons.lang3.StringUtils;
import org.easylenium.core.interfaces.Validate;
import org.easylenium.core.xml.NodeElement;
import org.easylenium.core.xml.exception.RequirementException;
import org.easylenium.core.xml.exception.TagUnequalsException;
import org.easylenium.core.xml.util.ConvertNodeIntoString;
import org.w3c.dom.Element;

public class TestCaseNode implements Validate {

	private static final String TAG_NAME = "TestCase";

	private static final String ATTR_ID = "id";

	private static final String ATTR_TEMPLATE = "template";

	private NodeElement node;

	public TestCaseNode(NodeElement node) {
		this.node = node;
	}

	public void validate() {
		if (!TAG_NAME.equalsIgnoreCase(node.getTagName()))
			throw new TagUnequalsException(TAG_NAME);
		
		if(StringUtils.isEmpty(getId()))
			throw new RequirementException("Exists an element that does't have an ID.");
		
	}

	public String getId() {
		return node.getAttribute(ATTR_ID);
	}

	public String getTemplate(){
		return node.getAttribute(ATTR_TEMPLATE);
	}
	
	public boolean hasTemplate() {
		return StringUtils.isNotEmpty(getTemplate());
	}
	
	public Element getElement(){
		return node.getElement();
	}
	
	@Override
	public String toString() {
		return new ConvertNodeIntoString(node.getElement()).doIt();
	}

	
}
