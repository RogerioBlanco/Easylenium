package org.easylenium.core.suitetest.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.easylenium.core.xml.NodeElement;
import org.easylenium.core.xml.exception.TagUnequalsException;

public class ScenarioNode
{

	private static final String TAG_NAME = "Scenario";

	private static final String ATTR_NAME = "name";

	private static final String ATTR_ACTIVE = "active";

	private static final String ATTR_DESCRIPTION = "description";

	private NodeElement rootNode;

	private List<StepNode> stepsNodes;

	private File file;

	public ScenarioNode(File file, NodeElement node)
	{
		this.file = file;
		this.rootNode = node;
		this.stepsNodes = readChildrenNodes();
	}

	private List<StepNode> readChildrenNodes()
	{
		List<StepNode> list = new ArrayList<StepNode>();

		for (NodeElement node : rootNode.getChildrenNodes())
		{

			StepNode stepNode = new StepNode(node);

			list.add(stepNode);
		}

		return list;
	}

	public void validate()
	{
		if (!TAG_NAME.equalsIgnoreCase(rootNode.getElement().getTagName()))
			throw new TagUnequalsException("The scenario tag in the file with name '%s' is not equals to '%s'.", file.getName(), TAG_NAME);

		for (StepNode node : stepsNodes)
			node.validade(file, getName());
	}

	public String getName()
	{
		return rootNode.getAttribute(ATTR_NAME);
	}

	public String getDescription()
	{
		return rootNode.getAttribute(ATTR_DESCRIPTION);
	}

	public Boolean isActive()
	{
		String value = rootNode.getAttribute(ATTR_ACTIVE);
		return Boolean.TRUE.toString().equalsIgnoreCase(value);
	}

	public List<StepNode> getStepsNodes()
	{
		return stepsNodes;
	}

	@Override
	public String toString()
	{
		return rootNode.toString();
	}
}
