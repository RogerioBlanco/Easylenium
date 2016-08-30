package org.easylenium.core.settings;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

import org.apache.commons.lang3.StringUtils;
import org.easylenium.core.interfaces.Validate;
import org.easylenium.core.settings.exception.SettingsValidationException;
import org.easylenium.core.settings.selenium.SeleniumSettings;

/**
 * <p>
 * General project settings
 * </p>
 * 
 * @author rogerionunes
 *
 */
public class Settings implements Validate{

	private String projectName;

	private String pathTestsSuites;

	private String pathTestsCases;

	private SeleniumSettings seleniumSettings;

	public void validate() {
		if (StringUtils.isEmpty(projectName))
			throw new SettingsValidationException("The name of project does not can be empty.");

		if (!validPath(pathTestsSuites))
			throw new SettingsValidationException("The directory path of test suites must be valid.");

		if (!validPath(pathTestsCases))
			throw new SettingsValidationException("The directory path of test cases must be valid.");

		if (seleniumSettings == null)
			throw new SettingsValidationException("Must be filled for specific settings for selenium.");

		seleniumSettings.validate();
	}

	private boolean validPath(String path) {

		try {
			Paths.get(path);
		} catch (InvalidPathException e) {
			return Boolean.FALSE;
		} catch (NullPointerException e) {
			return Boolean.FALSE;
		}

		return Boolean.TRUE;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPathTestsSuites() {
		return pathTestsSuites;
	}

	public void setPathTestsSuites(String pathTestsSuites) {
		this.pathTestsSuites = pathTestsSuites;
	}

	public String getPathTestsCases() {
		return pathTestsCases;
	}

	public void setPathTestsCases(String pathTestsCases) {
		this.pathTestsCases = pathTestsCases;
	}

}
