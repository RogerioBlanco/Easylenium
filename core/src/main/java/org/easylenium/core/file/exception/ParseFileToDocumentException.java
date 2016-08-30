package org.easylenium.core.file.exception;

import java.io.File;

import org.xml.sax.SAXException;

public class ParseFileToDocumentException extends RuntimeException {

	private static final long serialVersionUID = 241873415270362156L;

	public ParseFileToDocumentException(File file, SAXException e) {
		super(String.format("Error to parse the file %s into document.", file.getAbsolutePath()), e);
	}

}
