package de.robadd.logfilter.ui.tabs;

import java.util.Arrays;
import java.util.Collection;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import de.robadd.logfilter.logtypes.LogConfiguration;
import de.robadd.logfilter.logtypes.serverlog.ServerLogConfiguration;
import de.robadd.logfilter.model.LogLevel;
import de.robadd.logfilter.ui.filter.ClassFilterPanel;
import de.robadd.logfilter.ui.filter.DateFilterPanel;
import de.robadd.logfilter.ui.filter.FilterPanel;
import de.robadd.logfilter.ui.filter.LogLevelFilterPanel;

public class ServerLogPanel extends LogPanel
{
	public ServerLogPanel()
	{
		init();
	}

	private static final long serialVersionUID = -2161393816161110760L;

	@Override
	public String getTitle()
	{
		return "Serverlog";
	}

	@Override
	public Icon getIcon()
	{
		return new ImageIcon(ClassLoader.getSystemResource("icons/connect.png"));
	}

	@Override
	protected Collection<FilterPanel<?>> getFilterPanels()
	{
		LogLevelFilterPanel loglevelFilter = new LogLevelFilterPanel();
		loglevelFilter.setValues(Arrays.asList(LogLevel.values()));
		return Arrays.asList(loglevelFilter, new DateFilterPanel(), new ClassFilterPanel());
	}

	@Override
	public LogConfiguration getLogConfiguration()
	{
		return new ServerLogConfiguration();
	}

	@Override
	public boolean isImplemented()
	{
		return true;
	}
}
