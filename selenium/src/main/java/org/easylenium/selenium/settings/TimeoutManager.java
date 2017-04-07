package org.easylenium.selenium.settings;

public class TimeoutManager {

	private Timeout implicitlyWait;

	private Timeout pageLoadTimeout;

	private Timeout scriptTimeout;

	public TimeoutManager(Timeout implicitlyWait, Timeout pageLoadTimeout, Timeout scriptTimeout) {
		this.implicitlyWait = implicitlyWait;
		this.pageLoadTimeout = pageLoadTimeout;
		this.scriptTimeout = scriptTimeout;
	}

	public Timeout getImplicitlyWait() {
		return implicitlyWait;
	}

	public Timeout getPageLoadTimeout() {
		return pageLoadTimeout;
	}

	public Timeout getScriptTimeout() {
		return scriptTimeout;
	}

}
