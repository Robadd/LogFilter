package de.robadd.logfilter.logtypes.ebay;

import de.robadd.logfilter.model.Event;

public class EbayEvent extends Event
{
	@Override
	public String getRequestMethod()
	{
		return message.getRequestMethod();
	}
}
