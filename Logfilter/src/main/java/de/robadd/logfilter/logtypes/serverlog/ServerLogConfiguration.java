package de.robadd.logfilter.logtypes.serverlog;

import org.xml.sax.Attributes;

import de.robadd.logfilter.logtypes.LogConfiguration;
import de.robadd.logfilter.logtypes.ProcessingType;
import de.robadd.logfilter.model.AsynchronousEventBuilder;
import de.robadd.logfilter.model.EventBuilder;
import de.robadd.logfilter.model.EventFactory;
import de.robadd.logfilter.model.IndexBuilder;

public class ServerLogConfiguration implements LogConfiguration<ServerEvent>
{
	@Override
	public EventFactory<ServerEvent> getEventFactory()
	{
		return ServerEvent::new;
	}

	@Override
	public IndexBuilder getIndexBuilder()
	{
		return ServerLogIndex::new;
	}

	@Override
	public EventBuilder<ServerEvent> getEventBuilder()
	{
		return new AsynchronousEventBuilder<ServerEvent>()
		{
			@Override
			public void fillElement(final String uri, final String localName, final String qName,
					final Attributes attributes)
			{
				// Nothing to do
			}

			@Override
			public EventFactory<ServerEvent> getEventFactory()
			{
				return ServerEvent::new;
			}
		};

	}

	@Override
	public ProcessingType getProcessingType()
	{
		return ProcessingType.SYNC;
	}

}
