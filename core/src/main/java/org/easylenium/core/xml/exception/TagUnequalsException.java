package org.easylenium.core.xml.exception;

import java.io.File;

public class TagUnequalsException extends RuntimeException {

	private static final long serialVersionUID = 6023139398406389334L;

	public TagUnequalsException(String tagName, File file) {
		super(String.format("The root element in the file '%s' is not equals to '%s'", file, tagName));
	}

}
