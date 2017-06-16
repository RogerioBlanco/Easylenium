package org.easylenium.core.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LoadFiles {

	private Path directory;

	public LoadFiles(Path directory) {
		this.directory = directory;
	}

	public Collection<File> load() {
		try (Stream<Path> pathWalk = Files.walk(directory)) {
			return pathWalk.filter(Files::isRegularFile).map(path -> path.toFile()).collect(Collectors.toList());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
