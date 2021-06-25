package de.robadd.logfilter.logtypes.shopware;

import de.robadd.logfilter.FilterMethod;
import de.robadd.logfilter.model.Event;
import de.robadd.logfilter.ui.filter.CallTypeFilterPanel;

public class ShopwareEvent extends Event
{
	@FilterMethod(CallTypeFilterPanel.class)
	public String getRequestMethod()
	{
		return message.getRequestMethod();
	}
}
