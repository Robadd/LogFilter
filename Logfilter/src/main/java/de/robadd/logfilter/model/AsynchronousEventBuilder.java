package de.robadd.logfilter.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.xml.sax.Attributes;

public abstract class AsynchronousEventBuilder<T extends Event> implements EventBuilder<T>
{
	protected CompletableFuture<T> event;

	protected AsynchronousEventBuilder()
	{
		super();
		event = CompletableFuture.supplyAsync(() -> getEventFactory().build());
	}

	@Override
	public void addToIndex(final Index index)
	{
		event.thenAccept(index::addEvent);
	}

	@Override
	public void fillEvent(final Attributes attributes)
	{
		String value = attributes.getValue("level");
		String value2 = attributes.getValue("thread");
		String value3 = attributes.getValue("logger");
		String value4 = attributes.getValue("timestamp");

		event.thenApplyAsync(ev ->
		{
			ev.setLevel(LogLevel.getByValue(value));
			ev.setThread(value2);
			ev.setLogger(value3);
			try
			{
				Date date = new SimpleDateFormat("dd.MM.yyyy H:mm:ss").parse(value4);
				ev.setTimestamp(date);
			}
			catch (ParseException e)
			{
				e.printStackTrace();
			}
			return ev;
		});
	}

	@Override
	public void fillLocationInfo(final Attributes attributes)
	{
		String value = attributes.getValue("class");
		String value2 = attributes.getValue("method");
		String value3 = attributes.getValue("file");
		String value4 = attributes.getValue("line");
		event.thenApplyAsync(ev ->
		{
			ev.setClazz(value);
			ev.setMethod(value2);
			ev.setFile(value3);
			try
			{
				if (value4 != null)
				{
					ev.setLine(Integer.valueOf(value4.replace(".", "")));
				}
			}
			catch (NumberFormatException e)
			{
				e.printStackTrace();
			}
			return ev;
		});
	}

	@Override
	public T getEvent()
	{
		try
		{
			return event.get();
		}
		catch (InterruptedException | ExecutionException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void messageCharacters(final char[] ch, final int start, final int length)
	{
		event.thenApplyAsync(ev ->
		{
			ev.messageCharacter(ch, start, length);
			return ev;
		});
	}

	@Override
	public void throwableCharacters(final char[] ch, final int start, final int length)
	{
		event.thenApplyAsync(ev ->
		{
			ev.setThrowable(new String(ch, start, length));
			return ev;
		});
	}
}
