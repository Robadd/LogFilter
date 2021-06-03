package de.robadd.logfilter.logtypes.hitmeister;

import org.xml.sax.Attributes;

import de.robadd.logfilter.logtypes.LogConfiguration;
import de.robadd.logfilter.model.Event;
import de.robadd.logfilter.model.EventBuilder;
import de.robadd.logfilter.model.IndexBuilder;

public class HitmeisterLogConfiguration implements LogConfiguration
{

	@Override
	public void fillElement(final Event event, final String uri, final String localName, final String qName,
			final Attributes attributes)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public EventBuilder getEventBuilder()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IndexBuilder getIndexBuilder()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
