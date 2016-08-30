package org.easylenium.core.file;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;

public class LoadRecursivelyFiles {

	private String directory;

	public LoadRecursivelyFiles(String directory) {
		this.directory = directory;
	}

	public Map<String, File> loadFiles() {
		return loadFiles(new File(directory));
	}

	private Map<String, File> loadFiles(File directory) {
		HashMap<String, File> map = new HashMap<String, File>();

		for (File file : directory.listFiles()) {
			if (file.isDirectory()) {
				map.putAll(loadFiles(file));
			} else {
				map.put(FilenameUtils.removeExtension(file.getName()).toLowerCase(), file);
			}
		}

		return map;
	}

}
