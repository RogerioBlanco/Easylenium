package org.easylenium.core.xml.util;

import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.easylenium.core.file.exception.ParseFileToDocumentException;
import org.easylenium.core.testcase.xml.exception.ConvertNodeToStringException;
import org.w3c.dom.Node;

public class ConvertNodeIntoString {

	private static final Transformer TRANSFORMER = newTranstorm();
	
	
	private Node node;

	public ConvertNodeIntoString(Node node) {
		this.node = node;
	}

	private final static Transformer newTranstorm() {
		try {
			Transformer transform = TransformerFactory.newInstance().newTransformer();
			transform.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			return transform;
		} catch (TransformerConfigurationException e) {
			throw new ParseFileToDocumentException("Error in configuration of the transform.", e);
		}
	}

	public String doIt() {
		StreamResult streamResult = new StreamResult(new StringWriter());

		try {
			TRANSFORMER.transform(new DOMSource(node), streamResult);
		} catch (TransformerException e) {
			throw new ConvertNodeToStringException("Error has occurred to convert the node into text.");
		}
		
		return streamResult.getWriter().toString();
	}

}
