package org.easylenium.core.suitetest.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.easylenium.core.xml.NodeElement;
import org.easylenium.core.xml.RootNode;
import org.easylenium.core.xml.exception.TagUnequalsException;

public class SuiteTestRootNode {

	private static final String TAG_NAME = "SuiteTestCase";
	
	private static final String ATTR_NAME = "name";
	
	private static final String ATTR_DESCRIPTION = "description";
	
	private static final String ATTR_ACTIVE = "active";
	
	private RootNode rootNode;

	private File file;

	private Collection<ScenarioNode> scenariosNodes;
	
	public SuiteTestRootNode(File file, RootNode rootNode) {
		this.file = file;
		this.rootNode = rootNode;
		this.scenariosNodes = readChildrenNodes();
	}

	private Collection<ScenarioNode> readChildrenNodes() {
		Collection<ScenarioNode> list = new ArrayList();
		
		for(NodeElement node : rootNode.getChildrenNodes()){
			
			ScenarioNode scenarioNode = new ScenarioNode(file, node);
			
			scenarioNode.validate();
			
			list.add(scenarioNode);
		}
		
		return list;
	}
	
	public void validate() {
		if (!TAG_NAME.equalsIgnoreCase(rootNode.getElement().getTagName()))
			throw new TagUnequalsException("The root element in the file '%s' is not equals to '%s'.", file.getName(), TAG_NAME);
		
	}

	public String getName() {
		return rootNode.getAttribute(ATTR_NAME);
	}
	
	public String getDescription() {
		return rootNode.getAttribute(ATTR_DESCRIPTION);
	}
	
	public Boolean isActive(){
		String value = rootNode.getAttribute(ATTR_ACTIVE);
		return Boolean.TRUE.toString().equalsIgnoreCase(value);
	}

	public Collection<ScenarioNode> getScenariosNodes() {
		return scenariosNodes;
	}

}
