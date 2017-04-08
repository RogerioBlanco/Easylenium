package org.easylenium.core.file;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;

public class LoadFiles
{

	private String directory;

	public LoadFiles(String directory)
	{
		this.directory = directory;
	}

	public Collection<File> loadRecursively()
	{
		return loadRecursively(new File(directory));
	}

	private Collection<File> loadRecursively(File directory)
	{
		HashSet<File> set = new HashSet<File>();

		for (File file : directory.listFiles())
		{
			if (file.isDirectory())
			{
				set.addAll(loadRecursively(file));
			} else
			{
				set.add(file);
			}
		}

		return set;
	}

}
