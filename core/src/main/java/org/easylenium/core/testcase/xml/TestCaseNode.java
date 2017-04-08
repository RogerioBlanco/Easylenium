package org.easylenium.core.testcase.xml;

import org.apache.commons.lang3.StringUtils;
import org.easylenium.core.xml.NodeElement;
import org.easylenium.core.xml.exception.RequirementException;
import org.easylenium.core.xml.exception.TagUnequalsException;
import org.w3c.dom.Element;

public class TestCaseNode
{

	private static final String TAG_NAME = "TestCase";

	private static final String ATTR_ID = "id";

	private static final String ATTR_TEMPLATE = "template";

	private NodeElement node;

	public TestCaseNode(NodeElement node)
	{
		this.node = node;
	}

	public void validate()
	{
		if (!TAG_NAME.equalsIgnoreCase(node.getTagName()))
			throw new TagUnequalsException("The tag name of this test case:\n'%s'\nIsn't equals to tag name '%s'.", node.toString(), TAG_NAME);

		if (StringUtils.isBlank(getId()))
			throw new RequirementException("This test case:\n'%s'\nDon't have an ID.", node.toString());

	}

	public String getId()
	{
		return node.getAttribute(ATTR_ID);
	}

	public String getTemplate()
	{
		return node.getAttribute(ATTR_TEMPLATE);
	}

	public boolean hasTemplate()
	{
		return StringUtils.isNotBlank(getTemplate());
	}

	public Element getElement()
	{
		return node.getElement();
	}

	@Override
	public String toString()
	{
		return node.toString();
	}

}
