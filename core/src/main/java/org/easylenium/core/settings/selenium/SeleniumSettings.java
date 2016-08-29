package org.easylenium.core.settings.selenium;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.easylenium.core.interfaces.Validade;
import org.easylenium.core.settings.Timeout;
import org.easylenium.core.settings.exception.SettingsValidationException;

public class SeleniumSettings implements Validade {

	private Collection<Browser> browsers;

	private Timeout timeout;

	public SeleniumSettings() {
		browsers = Collections.emptyList();
	}

	public void validate() {
		if (browsers.isEmpty())
			throw new SettingsValidationException("It must be selected at least one browser");
		
		if (timeout == null)
			throw new SettingsValidationException("You must configure the default timeout.");
	}

	public Collection<Browser> getBrowsers() {
		return browsers;
	}

	public void addBrowser(Browser... browsers) {
		this.browsers.addAll(Arrays.asList(browsers));
	}

	public Timeout getTimeout() {
		return timeout;
	}

	public void setTimeout(Timeout timeout) {
		this.timeout = timeout;
	}
}
