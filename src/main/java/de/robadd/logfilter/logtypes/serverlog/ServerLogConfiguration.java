package de.robadd.logfilter.logtypes.serverlog;

import de.robadd.logfilter.logtypes.LogConfiguration;
import de.robadd.logfilter.model.Event;
import de.robadd.logfilter.model.EventBuilder;
import de.robadd.logfilter.model.IndexBuilder;
import de.robadd.logfilter.ui.tabs.LogPanel;
import de.robadd.logfilter.ui.tabs.ServerLogPanel;

public class ServerLogConfiguration implements LogConfiguration
{

	@Override
	public LogPanel getPanel()
	{
		return new ServerLogPanel();
	}

	@Override
	public void fillElement(final Event event)
	{
		// Not needed
	}

	@Override
	public EventBuilder getEventBuilder()
	{
		return ServerEvent::new;
	}

	@Override
	public IndexBuilder getIndexBuilder()
	{
		return ServerLogIndex::new;
	}

}
