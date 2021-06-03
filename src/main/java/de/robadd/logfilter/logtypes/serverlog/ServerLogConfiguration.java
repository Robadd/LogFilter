package de.robadd.logfilter.logtypes.serverlog;

import org.xml.sax.Attributes;

import de.robadd.logfilter.logtypes.LogConfiguration;
import de.robadd.logfilter.model.Event;
import de.robadd.logfilter.model.EventBuilder;
import de.robadd.logfilter.model.IndexBuilder;

public class ServerLogConfiguration implements LogConfiguration
{

	@Override
	public void fillElement(final Event event, final String uri, final String localName, final String qName,
			final Attributes attributes)
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
