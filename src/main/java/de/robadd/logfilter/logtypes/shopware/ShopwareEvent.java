package de.robadd.logfilter.logtypes.shopware;

import de.robadd.logfilter.model.Event;

public class ShopwareEvent extends Event
{
	@Override
	public String getRequestMethod()
	{
		return message.getRequestMethod();
	}
}
