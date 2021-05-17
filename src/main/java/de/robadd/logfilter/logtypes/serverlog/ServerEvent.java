package de.robadd.logfilter.logtypes.serverlog;

import de.robadd.logfilter.model.Event;

public class ServerEvent extends Event
{

	public ServerEvent()
	{
		super();
		message = new ServerMessage();
	}
}
