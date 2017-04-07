package org.easylenium.core.suitetest.xml;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.easylenium.core.xml.NodeElement;
import org.easylenium.core.xml.exception.AttributeException;
import org.easylenium.core.xml.exception.TagUnequalsException;

public class StepNode {

	private static final String TAG_NAME = "Step";
	
	private static final String ATTR_NAME = "name";
	
	private static final String ATTR_REFERENCE = "ref";

	private static final String ATTR_EXECUTOR = "executor";
	
	private NodeElement node;

	public StepNode(NodeElement node) {
		this.node = node;
	}

	public void validade(File file, String scenarioName) {
		String defaultError = String.format("Have a problem in some step of the scenario '%s' in the file '%s'.\nError: ", file.getName(), scenarioName);

		if (!TAG_NAME.equalsIgnoreCase(node.getElement().getTagName()))
			throw new TagUnequalsException(defaultError + "The step tag '%s' is not equals to '%s'.", node.getElement().getTagName(), TAG_NAME);
		
		if (!StringUtils.isNoneBlank(getName(), getReference(), getExecutor()))
			throw new AttributeException(defaultError + "Isn't possible create a step if the attributes '%s', '%s' and '%s' all empty.", ATTR_NAME, ATTR_REFERENCE, ATTR_EXECUTOR);

		if (StringUtils.isNoneBlank(getName(), getReference(), getExecutor()))
			throw new AttributeException(defaultError + "To create an step is necessary choice OR fill the attributes '%s' AND '%s' OR the attribute '%s'", ATTR_NAME, ATTR_REFERENCE, ATTR_EXECUTOR);
		
		if (StringUtils.isAnyBlank(getName(), getReference())) {
			if (StringUtils.isBlank(getName())) {
				throw new AttributeException(defaultError + "It's necessary fill the attribute '%s'", ATTR_NAME); 
			} else {
				throw new AttributeException(defaultError + "It's necessary fill the attribute '%s'", ATTR_REFERENCE);
			}
		}
	}
	
	public String getName() {
		return node.getAttribute(ATTR_NAME);
	}
	
	public String getReference() {
		return node.getAttribute(ATTR_REFERENCE);
	}
	
	public String getExecutor() {
		return node.getAttribute(ATTR_EXECUTOR);
	}

	public boolean isExecutor() {
		return StringUtils.isNotBlank(getExecutor());
	}
	
	@Override
	public String toString() {
		return node.toString();
	}
}
