package org.easylenium.core.xml;

import java.util.ArrayList;
import java.util.Collection;

import org.easylenium.core.xml.util.ConvertNodeIntoString;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NodeElement
{

	private Node node;

	private String text = "NodeElement";

	public NodeElement(Node node)
	{
		this.node = node;
		if (isElement())
			this.text = new ConvertNodeIntoString(getElement()).doIt();
	}

	public boolean isElement()
	{
		return node.getNodeType() == Node.ELEMENT_NODE;
	}

	public String getTagName()
	{
		return getElement().getTagName();
	}

	public Element getElement()
	{
		return (Element) node;
	}

	public String getAttribute(String name)
	{
		return getElement().getAttribute(name);
	}

	public Collection<NodeElement> getChildrenNodes()
	{
		Collection<NodeElement> list = new ArrayList<NodeElement>();

		NodeList childNodes = getElement().getChildNodes();
		for (int index = 0; index < childNodes.getLength(); index++)
		{
			NodeElement node = new NodeElement(childNodes.item(index));

			if (node.isElement())
				list.add(node);
		}

		return list;
	}

	@Override
	public String toString()
	{
		return text;
	}

}
