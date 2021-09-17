package de.robadd.logfilter.logtypes.serverlog;

import de.robadd.logfilter.model.Message;

public class ServerMessage implements Message
{
	StringBuilder msg = new StringBuilder();

	@Override
	public void character(final String str)
	{
		msg.append(str);
	}

	@Override
	public String toString()
	{
		return msg.toString();
	}

	@Override
	public String getRequestMethod()
	{
		return "";
	}
}
