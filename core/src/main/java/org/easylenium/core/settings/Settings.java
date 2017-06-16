package org.easylenium.core.settings;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.lang3.StringUtils;
import org.easylenium.core.settings.exception.SettingsValidationException;

/**
 * <p>
 * General project settings
 * </p>
 * 
 * @author rogerionunes
 *
 */
public class Settings {

	private String projectName;

	private Path testsSuitesDirectory;

	private Path testsCasesDirectory;

	public void validate() {
		if (StringUtils.isEmpty(projectName))
			throw new SettingsValidationException("The name of project does not can be empty.");

		if (testsSuitesDirectory == null)
			throw new SettingsValidationException("The directory path of test suites cannot be null.");

		if (testsCasesDirectory == null)
			throw new SettingsValidationException("The directory path of test cases cannot be null.");
	}

	private Path getPath(String path) {
		try {
			return Paths.get(path);
		} catch (Exception e) {
			throw new SettingsValidationException("The path '%s' must be valid.", path);
		}
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public void setPathTestsSuites(String pathTestsSuites) {
		this.testsSuitesDirectory = getPath(pathTestsSuites);
	}

	public void setPathTestsCases(String pathTestsCases) {
		this.testsCasesDirectory = getPath(pathTestsCases);
	}

	public Path getTestsCasesDirectory() {
		return testsCasesDirectory;
	}

	public Path getTestsSuitesDirectory() {
		return testsSuitesDirectory;
	}
}
