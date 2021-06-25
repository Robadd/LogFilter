package de.robadd.logfilter.logtypes.ebay;

import de.robadd.logfilter.FilterMethod;
import de.robadd.logfilter.model.Event;
import de.robadd.logfilter.ui.filter.CallTypeFilterPanel;

public class EbayEvent extends Event
{
	@FilterMethod(CallTypeFilterPanel.class)
	public String getRequestMethod()
	{
		return message.getRequestMethod();
	}

}
