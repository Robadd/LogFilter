package de.robadd.logfilter.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.xml.sax.Attributes;

public abstract class SynchronousEventBuilder<T extends Event> implements EventBuilder<T>
{
	protected T event;

	protected SynchronousEventBuilder()
	{
		super();
	}

	@Override
	public void addToIndex(final Index index)
	{
		index.addEvent(event);
	}

	@Override
	public void fillEvent(final Attributes attributes)
	{
		event.setLevel(LogLevel.getByValue(attributes.getValue("level")));
		event.setLevel(LogLevel.getByValue(attributes.getValue("level")));
		event.setThread(attributes.getValue("thread"));
		event.setLogger(attributes.getValue("logger"));
		try
		{
			Date date = null;
			date = new SimpleDateFormat("dd.MM.yyyy H:mm:ss").parse(attributes.getValue("timestamp"));
			event.setTimestamp(date);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void fillLocationInfo(final Attributes attributes)
	{
		event.setClazz(attributes.getValue("class"));
		event.setMethod(attributes.getValue("method"));
		event.setFile(attributes.getValue("file"));
		try
		{
			event.setLine(Integer.valueOf(attributes.getValue("line").replace(".", "")));
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public T getEvent()
	{
		return event;
	}

	@Override
	public void messageCharacters(final char[] ch, final int start, final int length)
	{
		event.messageCharacter(ch, start, length);
	}

	@Override
	public void throwableCharacters(final char[] ch, final int start, final int length)
	{
		event.setThrowable(new String(ch, start, length));
	}
}
