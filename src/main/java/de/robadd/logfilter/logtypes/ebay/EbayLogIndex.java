package de.robadd.logfilter.logtypes.ebay;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import de.robadd.logfilter.model.Event;
import de.robadd.logfilter.model.Index;

public class EbayLogIndex extends Index
{
	private Set<String> callTypes = new HashSet<>();

	@Override
	public void addLogTypeSpecifics(final Event msg)
	{
		if (msg.getMessage() == null)
		{
			return;
		}
		String requestMethod = msg.getMessage().getRequestMethod();
		if (requestMethod != null)
		{
			callTypes.add(requestMethod);
		}
	}

	public Collection<String> getCallTypes()
	{
		return callTypes;
	}
}
