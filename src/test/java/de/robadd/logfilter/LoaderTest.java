package de.robadd.logfilter;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import de.robadd.logfilter.ui.tabs.LogPanel;

public class LoaderTest
{
	@Test
	public void test()
	{
		List<LogPanel> logPanels = InterfaceLoader.getLogPanels();
		Assertions.assertThat(logPanels).hasSameSizeAs(InterfaceLoader.LogPanels.values());

	}
}
