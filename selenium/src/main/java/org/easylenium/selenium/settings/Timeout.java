package org.easylenium.selenium.settings;

import java.util.concurrent.TimeUnit;

public class Timeout {

	private TimeUnit timeUnit;

	private Long time;

	public Timeout(TimeUnit timeUnit, Long time) {
		this.timeUnit = timeUnit;
		this.time = time;
	}

	public Long getTime() {
		return time;
	}

	public TimeUnit getTimeUnit() {
		return timeUnit;
	}

}
