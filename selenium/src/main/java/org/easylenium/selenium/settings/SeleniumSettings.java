package org.easyleniu.selenium.settings;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.easyleniu.selenium.settings.exception.SeleniumSettingsValidationException;
import org.easylenium.core.settings.Settings;

public class SeleniumSettings extends Settings {

	private Collection<Browser> browsers;

	private TimeoutManager timeoutManager;

	public SeleniumSettings() {
		browsers = Collections.emptyList();
	}

	public void validate() {
		super.validate();
		
		if (browsers.isEmpty())
			throw new SeleniumSettingsValidationException("It must be selected at least one browser");

		if (timeoutManager == null)
			throw new SeleniumSettingsValidationException("You must configure the default timeout.");
	}

	public Collection<Browser> getBrowsers() {
		return browsers;
	}

	public void addBrowser(Browser... browsers) {
		this.browsers.addAll(Arrays.asList(browsers));
	}

	public void setTimeoutManager(TimeoutManager timeoutManager) {
		this.timeoutManager = timeoutManager;
	}

	public TimeoutManager getTimeout() {
		return timeoutManager;
	}

}
