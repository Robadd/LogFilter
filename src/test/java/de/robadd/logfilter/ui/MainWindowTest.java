package de.robadd.logfilter.ui;

import static org.assertj.core.api.Assertions.assertThat;

import javax.swing.JTabbedPane;

import org.junit.Test;

import de.robadd.logfilter.InterfaceLoader;

public class MainWindowTest
{
	@Test
	public void mainTest()
	{
		MainWindow window = new MainWindow();
		JTabbedPane frame = window.getFilterPanelFrame();
		assertThat(frame).extracting(a -> a.getTabCount()).isEqualTo(InterfaceLoader.getLogPanels().size());

	}

	@Test
	public void statusTest() throws InterruptedException
	{
		MainWindow window = new MainWindow();
		assertThat(window.getStatus().getText()).isEqualTo("Status");
		window.setStatus("Foobar");
		Thread.sleep(1000);
		assertThat(window.getStatus().getText()).isEqualTo("Foobar");
	}
}
