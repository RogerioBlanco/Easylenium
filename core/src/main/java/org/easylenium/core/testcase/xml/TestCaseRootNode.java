package org.easylenium.core.testcase.xml;

import java.io.File;
import java.util.Collection;
import java.util.Collections;

import org.easylenium.core.util.IPredicate;
import org.easylenium.core.util.Predicate;
import org.easylenium.core.xml.NodeElement;
import org.easylenium.core.xml.RootNode;
import org.easylenium.core.xml.exception.TagUnequalsException;

public class TestCaseRootNode {

	private static final String TAG_NAME = "TestCaseData";

	private static final String ATTR_EXECUTOR_CLASS = "executor-class";

	private static final String ATTR_DATA_CLASS = "data-class";

	private RootNode rootNode;
	
	private Collection<TestCaseNode> childrenNodes;

	public TestCaseRootNode(RootNode rootNode) {
		this.rootNode = rootNode;
		this.childrenNodes = readChildrenNodes();
	}

	public void validate(File file) {
		if (!TAG_NAME.equalsIgnoreCase(rootNode.getElement().getTagName()))
			throw new TagUnequalsException("The root element in the file '%s' is not equals to '%s'", file.getName(), TAG_NAME);
	}

	public String getExecutorClass() {
		return rootNode.getAttribute(ATTR_EXECUTOR_CLASS);
	}

	public String getDataClass() {
		return rootNode.getAttribute(ATTR_DATA_CLASS);
	}
	
	private Collection<TestCaseNode> readChildrenNodes() {
		Collection<TestCaseNode> list = Collections.emptyList();
		
		for(NodeElement node : rootNode.getChildrenNodes()){
			
			TestCaseNode testCaseNode = new TestCaseNode(node);
			
			testCaseNode.validate();
			
			list.add(testCaseNode);
		}
		
		return list;
	}

	public Collection<TestCaseNode> getChildrenNodes() {
		return childrenNodes;
	}

	public Collection<TestCaseNode> getChildrenNodesWithoutAttributeTemplate() {
		return Predicate.filter(getChildrenNodes(), new FilterNodesNodesWithoutAttributeTemplate());
	}

	public Collection<TestCaseNode> getChildrenNodesWithAttributeTemplate() {
		return Predicate.filterInverse(getChildrenNodes(), new FilterNodesNodesWithoutAttributeTemplate());
	}
	
	private final static class FilterNodesNodesWithoutAttributeTemplate implements IPredicate<TestCaseNode> {
		public boolean apply(TestCaseNode node) {
			return !node.hasTemplate();
		}
	}

}
