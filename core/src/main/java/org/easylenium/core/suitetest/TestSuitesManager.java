package org.easylenium.core.suitetest;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

import org.easylenium.core.custom.CustomizerGeneral;
import org.easylenium.core.executor.FactoryExecutor;
import org.easylenium.core.file.LoadFiles;
import org.easylenium.core.file.ParseFile;
import org.easylenium.core.file.exception.ParseFileToDocumentException;
import org.easylenium.core.suitetest.xml.SuiteTestRootNode;
import org.easylenium.core.testcase.TestCase;
import org.easylenium.core.util.Table;
import org.easylenium.core.xml.RootNode;
import org.xml.sax.SAXException;

import junit.framework.TestSuite;

public class TestSuitesManager
{

	private Path path;

	private Table<String, String, TestCase<?>> table;

	private FactoryExecutor factory;

	public TestSuitesManager(Path path, Table<String, String, TestCase<?>> table, FactoryExecutor factory)
	{
		this.path = path;
		this.table = table;
		this.factory = factory;
	}

	public Collection<TestSuite> createAllTestsSuites(CustomizerGeneral custom)
	{
		Collection<TestSuite> testsSuites = new ArrayList<TestSuite>();

		Collection<File> files = new LoadFiles(path).load();

		for (File file : files)
		{
			try
			{
				SuiteTestRootNode rootNode = new SuiteTestRootNode(file, new RootNode(new ParseFile(file).toDocument()));

				if (rootNode.isActive())
				{
					SuiteTest suiteTest = new SuiteTest(rootNode, table, factory);

					suiteTest.validate();

					testsSuites.add(suiteTest.toTestSuite(custom));
				}
			} catch (SAXException e)
			{
				throw new ParseFileToDocumentException(file, e);
			}
		}

		return testsSuites;
	}

}
