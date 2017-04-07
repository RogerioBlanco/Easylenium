package org.easylenium.core.xml;

import java.util.ArrayList;
import java.util.Collection;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RootNode {

	private Document document;

	public RootNode(Document document) {
		this.document = document;
	}

	public String getAttribute(String name) {
		return getElement().getAttribute(name);
	}

	public Element getElement() {
		return (Element) document.getDocumentElement();
	}
	
	public boolean isElementNode() {
		return getElement().getNodeType() == Node.ELEMENT_NODE;
	}

	public Collection<NodeElement> getChildrenNodes() {
		Collection<NodeElement> list = new ArrayList();
		
		NodeList childNodes = getElement().getChildNodes();
		for (int index = 0; index < childNodes.getLength(); index++) {
			NodeElement node = new NodeElement(childNodes.item(index));
			
			if (node.isElement())
				list.add(node);
		}
		
		return list;
	}

}
