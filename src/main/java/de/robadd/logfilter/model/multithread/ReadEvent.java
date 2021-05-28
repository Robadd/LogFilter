package de.robadd.logfilter.model.multithread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.function.Function;

import org.xml.sax.Attributes;

import de.robadd.logfilter.logtypes.LogConfiguration;
import de.robadd.logfilter.model.Event;

public class ReadEvent
{
	private Map<String, Map<String, String>> attrMap = new HashMap<>();
	private String message;
	private String throwable;
	private EventSupplier eventSupplier;
	private Function<Event, Void> eventConsumer;
	private MessageSupplier msgSupplier;
	private Executor executor;

	public ReadEvent(final LogConfiguration config, final Executor executor)
	{
		eventSupplier = new EventSupplier(config);
		msgSupplier = new MessageSupplier(config);
		this.executor = executor;
	}

	public void startElement(final String qName, final Attributes argAttributes)
	{
		attrMap.putIfAbsent(qName, new HashMap<>());
		for (int i = 0; i < argAttributes.getLength(); i++)
		{
			attrMap.get(qName).put(argAttributes.getLocalName(i), argAttributes.getValue(i));
		}
	}

	public void addMessage(final String msg)
	{
		message = msg;
	}

	public void addThrowable(final String throwable)
	{
		this.throwable = throwable;
	}

	public void addEventConsumer(final Function<Event, Void> consumer)
	{
		eventConsumer = consumer;
	}

	public void complete()
	{
		eventSupplier.setAttributes(attrMap);
		msgSupplier.setMessage(message);
		eventSupplier.setThrowable(throwable);

		CompletableFuture<Void> future = CompletableFuture.supplyAsync(eventSupplier, executor).thenApplyAsync(
			msgSupplier, executor).thenApplyAsync(eventConsumer, executor);
		try
		{
			future.get();
		}
		catch (InterruptedException | ExecutionException e)
		{
			e.printStackTrace();
		}
	}
}
