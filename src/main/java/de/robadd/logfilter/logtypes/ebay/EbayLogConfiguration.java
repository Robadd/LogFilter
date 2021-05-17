package de.robadd.logfilter.logtypes.ebay;

import org.xml.sax.Attributes;

import de.robadd.logfilter.logtypes.LogConfiguration;
import de.robadd.logfilter.model.Event;
import de.robadd.logfilter.model.EventBuilder;
import de.robadd.logfilter.model.Index;
import de.robadd.logfilter.model.IndexBuilder;
import de.robadd.logfilter.ui.tabs.EbayLogPanel;
import de.robadd.logfilter.ui.tabs.LogPanel;

public class EbayLogConfiguration implements LogConfiguration
{

	@Override
	public LogPanel getPanel()
	{
		return new EbayLogPanel();
	}

	@Override
	public void fillElement(final Event event, final String uri, final String localName, final String qName,
			final Attributes attributes)
	{
		if ("message".equals(qName))
		{
			event.setMessage(new EbayMessage());
		}
	}

	@Override
	public EventBuilder getEventBuilder()
	{
		return new EventBuilder()
		{
			@Override
			public Event build()
			{
				return new EbayEvent();
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
				return new EbayLogIndex();
			}
		};
	}

}
