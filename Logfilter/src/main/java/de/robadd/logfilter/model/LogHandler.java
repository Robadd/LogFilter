package de.robadd.logfilter.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import de.robadd.logfilter.Translator;
import de.robadd.logfilter.logtypes.LogConfiguration;

public class LogHandler extends DefaultHandler
{
	private final LogConfiguration<?> config;
	private Type type;
	private Index index;

	private String elementName;
	private BufferedWriter bw;
	private Collection<EventFilter<Event, ?>> filters = new ArrayList<>();
	private File inputFile;
	private Integer handledEventCount = 0;
	private Integer totalEventCount = 0;
	private File outputFile;
	private Function<Integer, Void> progressDisplay;
	private EventBuilder<?> eventBuilder;

	public enum Type
	{
		READING, WRITING, DISPLAY;
	}

	public void setToDisplayMode()
	{
		throw new UnsupportedOperationException(Translator.getCurrent().get("NotImplemented"));
	}

	public void setToReadingMode()
	{
		type = Type.READING;
		index = config.getIndexBuilder().build();
	}

	public void setToWritingMode(final File out, final Collection<EventFilter<Event, ?>> argFilters)
	{
		type = Type.WRITING;
		index = config.getIndexBuilder().build();
		this.filters = argFilters;
		this.outputFile = out;
	}

	public LogHandler(final LogConfiguration<?> argConfig)
	{
		config = argConfig;
	}

	@Override
	public void startDocument() throws SAXException
	{
		totalEventCount = 0;
		handledEventCount = 0;
		eventBuilder = config.getEventBuilder();
		if (Type.READING.equals(type))
		{
			index = config.getIndexBuilder().build();
		}
		else if (Type.WRITING.equals(type))
		{
			try
			{
				bw = new BufferedWriter(new FileWriter(outputFile));
			}
			catch (IOException e)
			{
				e.printStackTrace();
				throw new SAXException(e);
			}
		}
	}

	@Override
	public void startElement(final String uri, final String localName, final String qName, final Attributes attributes)
			throws SAXException
	{
		elementName = qName;

		if (isEventElement())
		{
			eventBuilder = config.getEventBuilder();
			eventBuilder.fillEvent(attributes);
		}
		else if ("locationInfo".equals(qName))
		{
			eventBuilder.fillLocationInfo(attributes);
		}
		eventBuilder.fillElement(uri, localName, qName, attributes);
	}

	@Override
	public void characters(final char[] ch, final int start, final int length) throws SAXException
	{
		if (isMessageElement())
		{
			eventBuilder.messageCharacters(ch, start, length);
		}
		else if ("throwable".equals(elementName))
		{
			eventBuilder.throwableCharacters(ch, start, length);
		}
	}

	@Override
	public void endElement(final String uri, final String localName, final String qName) throws SAXException
	{
		if ("event".equals(qName))
		{
			if (Type.READING.equals(type))
			{
				eventBuilder.addToIndex(index);
			}
			else if (Type.WRITING.equals(type))
			{
				try
				{
					Event event2 = eventBuilder.getEvent();
					writeToStream(event2);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			totalEventCount++;
			if (progressDisplay != null)
			{
				progressDisplay.apply(handledEventCount);
			}
			eventBuilder = null;
		}
	}

	@Override
	public void endDocument() throws SAXException
	{
		if (Type.WRITING.equals(type))
		{
			try
			{
				bw.flush();
				bw.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
				throw new SAXException(e);
			}
		}
	}

	/**
	 * if elementname is event
	 *
	 * @return if elementname is event
	 */
	private boolean isEventElement()
	{
		return "event".equals(elementName);
	}

	/**
	 * if elementname is message
	 *
	 * @return if elementname is message
	 */
	private boolean isMessageElement()
	{
		return "message".equals(elementName);
	}

	/**
	 * @param inputFile the inputFile to set
	 */
	public void setInputFile(final File inputFile)
	{
		this.inputFile = inputFile;
	}

	protected void writeToStream(final Event event) throws IOException
	{
		for (EventFilter<Event, ?> eventFilter : filters)
		{
			boolean test = eventFilter.test(event);
			if (test)
			{
				continue;
			}
			return;
		}
		bw.write(event.getXML());
		bw.write('\n');
		bw.write('\n');
		handledEventCount++;
	}

	public Index getIndex()
	{
		return index;
	}

	public File getInputFile()
	{
		return inputFile;
	}

	public File getOutputFile()
	{
		return outputFile;
	}

	/**
	 * @return the handledEventCount
	 */
	public Integer getHandledEventCount()
	{
		return handledEventCount;
	}

	/**
	 * @return the totalEventCount
	 */
	public Integer getTotalEventCount()
	{
		return totalEventCount;
	}

	public void setProgressDisplayMethod(final Function<Integer, Void> func)
	{
		progressDisplay = func;
	}
}
