package de.robadd.logfilter.logtypes.serverlog;

import org.xml.sax.Attributes;

import de.robadd.logfilter.logtypes.LogConfiguration;
import de.robadd.logfilter.model.Event;
import de.robadd.logfilter.model.EventBuilder;
import de.robadd.logfilter.model.Index;
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
	public void fillElement(final Event event, final String uri, final String localName, final String qName,
			final Attributes attributes)
	{

	}

	@Override
	public EventBuilder getEventBuilder()
	{
		return new EventBuilder()
		{
			@Override
			public Event build()
			{
				return new ServerEvent();
			}
		};
	}

	@Override
	public IndexBuilder getIndexBuilder()
	{
		return new IndexBuilder()
		{
			@Override
			public Index build()
			{
				return new ServerLogIndex();
			}
		};
	}

}
