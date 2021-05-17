package de.robadd.logfilter.model;

import org.junit.Test;

import de.robadd.logfilter.InterfaceLoader;
import de.robadd.logfilter.ui.tabs.LogPanel;

public class LoaderTest
{
	@Test
	public void test()
	{
		for (LogPanel panel : InterfaceLoader.getLogPanels())
		{
			System.out.println(panel.getClass().getName());
		}

	}
}
