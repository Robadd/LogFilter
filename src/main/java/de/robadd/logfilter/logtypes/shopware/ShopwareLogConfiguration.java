package de.robadd.logfilter.logtypes.shopware;

import org.xml.sax.Attributes;

import de.robadd.logfilter.logtypes.LogConfiguration;
import de.robadd.logfilter.model.Event;
import de.robadd.logfilter.model.EventBuilder;
import de.robadd.logfilter.model.IndexBuilder;
import de.robadd.logfilter.ui.tabs.LogPanel;
import de.robadd.logfilter.ui.tabs.ShopwareLogPanel;

public class ShopwareLogConfiguration implements LogConfiguration
{

	@Override
	public LogPanel getPanel()
	{
		return new ShopwareLogPanel();
	}

	@Override
	public void fillElement(final Event event, final String uri, final String localName, final String qName,
			final Attributes attributes)
	{
		if ("message".equals(qName))
		{
			event.setMessage(new ShopwareMessage());
		}
	}

	@Override
	public EventBuilder getEventBuilder()
	{
		return ShopwareEvent::new;
	}

	@Override
	public IndexBuilder getIndexBuilder()
	{
		return ShopwareLogIndex::new;
	}

}
