package org.easylenium.core.xml;

import org.w3c.dom.Node;

public class NodeElement {

	private Node node;

	public NodeElement(Node node) {
		this.node = node;
	}

	public boolean isElement() {
		return node.getNodeType() == Node.ELEMENT_NODE;
	}

}
