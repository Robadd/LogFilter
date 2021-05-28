package de.robadd.logfilter.logtypes.ebay;

import de.robadd.logfilter.logtypes.LogConfiguration;
import de.robadd.logfilter.model.Event;
import de.robadd.logfilter.model.EventBuilder;
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
	public void fillElement(final Event event)
	{
		event.setMessage(new EbayMessage());
	}

	@Override
	public EventBuilder getEventBuilder()
	{
		return EbayEvent::new;
	}

	@Override
	public IndexBuilder getIndexBuilder()
	{
		return EbayLogIndex::new;
	}

}
