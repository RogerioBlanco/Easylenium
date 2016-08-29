package org.easylenium.core.settings;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

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
	
	private Collection<Browser> browsers;
	
	private String directoryPathSuite;

	private String directoryPathCaseTests;
	
	private TimeOut timeout;
	
	public Settings(){
		browsers = Collections.emptyList();
	}
	
	public void validate() {
		if (StringUtils.isEmpty(projectName))
			throw new SettingsValidationException("The name of project does not can be empty.");

		if(browsers.isEmpty())
			throw new SettingsValidationException("It must be selected at least one browser");
		
		if(!validPath(directoryPathSuite))
			throw new SettingsValidationException("");
		
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public Collection<Browser> getBrowsers() {
		return browsers;
	}
	
	public void addBrowser(Browser... browsers){
		this.browsers.addAll(Arrays.asList(browsers));
	}
	
	private boolean validPath(String path){
		
		try{
			Paths.get(path);
		}catch (InvalidPathException e){
			return Boolean.FALSE;
		}catch (NullPointerException e) {
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}
}
