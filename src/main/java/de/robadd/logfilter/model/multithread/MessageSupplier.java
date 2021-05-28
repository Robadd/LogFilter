package de.robadd.logfilter.model.multithread;

import java.util.function.UnaryOperator;

import de.robadd.logfilter.logtypes.LogConfiguration;
import de.robadd.logfilter.model.Event;

public class MessageSupplier implements UnaryOperator<Event>
{
	private String message;
	private LogConfiguration config;

	public MessageSupplier(final LogConfiguration config)
	{
		super();
		this.config = config;
	}

	public void setMessage(final String message)
	{
		this.message = message;
	}

	@Override
	public Event apply(final Event t)
	{
		config.fillElement(t);
		t.messageCharacter(message);
		return t;
	}

}
