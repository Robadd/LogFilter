package de.robadd.logfilter.ui.tabs;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.google.common.collect.Lists;

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
	private LogLevelFilterPanel loglevelFilter;
	private DateFilterPanel dateFilter;
	private ClassFilterPanel classFilterPanel;

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
		Collection<FilterPanel<?>> retVal = new ArrayList<>();
		dateFilter = new DateFilterPanel();
		loglevelFilter = new LogLevelFilterPanel();
		loglevelFilter.setValues(Lists.newArrayList(LogLevel.values()));
		classFilterPanel = new ClassFilterPanel();
		retVal.add(dateFilter);
		retVal.add(loglevelFilter);
		retVal.add(classFilterPanel);
		return retVal;
	}

	public Collection<String> getClasses()
	{
		return classFilterPanel.getSelectedValues();
	}

	public Collection<LogLevel> getLogLevels()
	{
		return loglevelFilter.getSelectedValues();
	}

	@Override
	protected LogConfiguration getLogConfiguration()
	{
		return new ServerLogConfiguration();
	}

	@Override
	public int priority()
	{
		return 0;
	}

	@Override
	public boolean isImplemented()
	{
		return true;
	}
}
