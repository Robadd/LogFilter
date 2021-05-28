package de.robadd.logfilter.model.multithread;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

import de.robadd.logfilter.logtypes.LogConfiguration;
import de.robadd.logfilter.model.Event;
import de.robadd.logfilter.model.LogLevel;

public class EventSupplier implements Supplier<Event>
{
	private Map<String, Map<String, String>> attributes = new HashMap<>();
	private String message;
	private String throwable;
	private LogConfiguration config;

	public EventSupplier(final LogConfiguration config)
	{
		super();
		this.config = config;
	}

	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(final Map<String, Map<String, String>> attributes)
	{
		this.attributes = attributes;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(final String message)
	{
		this.message = message;
	}

	/**
	 * @param throwable the throwable to set
	 */
	public void setThrowable(final String throwable)
	{
		this.throwable = throwable;
	}

	@Override
	public Event get()
	{
		Event event = config.getEventBuilder().build();

		Map<String, String> eventAttributes = attributes.get("event");
		Map<String, String> locationInfoAttributes = attributes.get("locationInfo");

		event.setLevel(LogLevel.getByValue(eventAttributes.get("level")));
		event.setThread(eventAttributes.get("thread"));
		event.setLogger(eventAttributes.get("logger"));
		event.setTimestamp(LocalDateTime.from(DateTimeFormatter.ofPattern("dd.MM.yyyy H:mm:ss", Locale.GERMAN).parse(
			eventAttributes.get("timestamp"))));

		event.setClazz(locationInfoAttributes.get("class"));
		event.setMethod(locationInfoAttributes.get("method"));
		event.setFile(locationInfoAttributes.get("file"));
		try
		{
			event.setLine(Integer.valueOf(locationInfoAttributes.get("line").replace(".", "")));
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
		}
		event.setThrowable(throwable);
		config.fillElement(event);
		event.getMessage().character(message);
		return event;
	}
}
