package de.robadd.logfilter.logtypes.hitmeister;

import org.xml.sax.Attributes;

import de.robadd.logfilter.logtypes.LogConfiguration;
import de.robadd.logfilter.logtypes.ProcessingType;
import de.robadd.logfilter.model.Event;
import de.robadd.logfilter.model.EventBuilder;
import de.robadd.logfilter.model.EventFactory;
import de.robadd.logfilter.model.IndexBuilder;
import de.robadd.logfilter.model.SynchronousEventBuilder;

public class HitmeisterLogConfiguration implements LogConfiguration<Event>
{

	@Override
	public EventFactory<Event> getEventFactory()
	{
		return null;
	}

	@Override
	public IndexBuilder getIndexBuilder()
	{
		return null;
	}

	@Override
	public EventBuilder<Event> getEventBuilder()
	{
		return new SynchronousEventBuilder<Event>()
		{

			@Override
			public void fillElement(final String uri, final String localName, final String qName,
					final Attributes attributes)
			{
				// Not yet implemented
			}

			@Override
			public EventFactory<Event> getEventFactory()
			{
				// TODO Auto-generated method stub
				return null;
			}

		};
	}

	@Override
	public ProcessingType getProcessingType()
	{
		return ProcessingType.SYNC;
	}
}
