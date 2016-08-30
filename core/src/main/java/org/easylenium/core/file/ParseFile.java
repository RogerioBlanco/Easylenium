package org.easylenium.core.file;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.easylenium.core.file.exception.ParseFileToDocumentException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class ParseFile {

	private final static DocumentBuilder DOCUMENT_BUILDER = documentBuilder();

	private File file;

	public ParseFile(File file) {
		this.file = file;
	}

	private final static DocumentBuilder documentBuilder() {
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new ParseFileToDocumentException("Error in configuration of the parse", e);
		}
	}

	public Document toDocument() throws SAXException {
		try {
			Document document = DOCUMENT_BUILDER.parse(file);
			
			document.normalize();
			
			return document;
		} catch (IOException e) {
			throw new ParseFileToDocumentException("Error to load the file.", e);
		}
	}

}
