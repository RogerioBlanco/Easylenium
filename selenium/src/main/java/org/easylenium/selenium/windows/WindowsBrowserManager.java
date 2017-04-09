package org.easylenium.selenium.windows;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.easylenium.core.executor.exception.TimeoutException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;

public class WindowsBrowserManager
{
	private ArrayList<String> windows;

	private int current;

	private WebDriver webDriver;

	private ListenerWebDriver listener;

	public WindowsBrowserManager(final WebDriver webDriver)
	{
		this.windows = new ArrayList<String>();
		this.webDriver = webDriver;

		this.listener = createListener(webDriver);
		this.listener.start();
	}

	private ListenerWebDriver createListener(final WebDriver web)
	{
		String currentWindows = web.getWindowHandle();

		windows.add(currentWindows);

		current = windows.indexOf(currentWindows);

		return new ListenerWebDriver(this);
	}

	public WindowsBrowserManager waitNewWindowBrowser(TimeUnit unit, Long time)
	{
		int original = windows.size();

		long init = System.nanoTime();

		long timeout = unit.toNanos(time);

		while (true)
		{
			Set<String> handlers = getWindowHandles();

			if (handlers.size() > original)
			{
				for (String window : handlers)
					if (!windows.contains(window))
						windows.add(window);
				break;
			}

			if ((System.currentTimeMillis() - init) > timeout)
				throw new TimeoutException("The timeout has been reached waiting the new browser window.");
		}

		return this;
	}

	public void closeAndPrevious()
	{
		close();

		previous();
	}

	public void closeAndNext()
	{
		close();

		current--;

		next();
	}

	private void close()
	{
		webDriver.close();

		windows.remove(current);
	}

	public void next() throws NoSuchWindowException
	{
		int index = current + 1;

		try
		{
			webDriver = webDriver.switchTo().window(windows.get(index));

		} catch (IndexOutOfBoundsException e)
		{
			throw new NoSuchWindowException("Don't exists next browser windows to switch.");
		}

		current = index;
	}

	public void previous()
	{
		try
		{
			int index = current - 1;

			webDriver = webDriver.switchTo().window(windows.get(index));

			current = index;
		} catch (IndexOutOfBoundsException e)
		{
			throw new RuntimeException("NÃ£o existe janelas anteriores para retornar.");
		} catch (NoSuchWindowException e)
		{
			throw new RuntimeException(e);
		}
	}

	public WebDriver current()
	{
		return webDriver;
	}

	private synchronized Set<String> getWindowHandles()
	{
		return webDriver.getWindowHandles();
	}

	private class ListenerWebDriver implements Runnable
	{

		private Thread thread;

		private WindowsBrowserManager manager;

		private ListenerWebDriver(WindowsBrowserManager manager)
		{
			this.manager = manager;
			this.thread = new Thread(this);
		}

		public void run()
		{
			while (true)
			{
				Set<String> handlers = manager.getWindowHandles();

				if (handlers.size() > manager.windows.size())
					for (String window : handlers)
						if (!manager.windows.contains(window))
							manager.windows.add(window);

				List<String> win = (List<String>) manager.windows.clone();

				for (String window : win)
					if (!handlers.contains(window))
						windows.remove(window);

				try
				{
					TimeUnit.MILLISECONDS.sleep(100);
				} catch (InterruptedException e)
				{
				}
			}
		}

		public void start()
		{
			thread.start();
		}
	}

}
