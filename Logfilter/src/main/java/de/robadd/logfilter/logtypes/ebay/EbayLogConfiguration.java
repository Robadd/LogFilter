package de.robadd.logfilter.logtypes.ebay;

import org.xml.sax.Attributes;

import de.robadd.logfilter.logtypes.LogConfiguration;
import de.robadd.logfilter.logtypes.ProcessingType;
import de.robadd.logfilter.model.AsynchronousEventBuilder;
import de.robadd.logfilter.model.EventBuilder;
import de.robadd.logfilter.model.EventFactory;
import de.robadd.logfilter.model.IndexBuilder;

public class EbayLogConfiguration implements LogConfiguration<EbayEvent>
{
    @Override
    public EventFactory<EbayEvent> getEventFactory()
    {
        return EbayEvent::new;
    }

    @Override
    public IndexBuilder getIndexBuilder()
    {
        return EbayLogIndex::new;
    }

    @Override
    public EventBuilder<EbayEvent> getEventBuilder()
    {
        return new AsynchronousEventBuilder<>()
        {
            @Override
            public void fillElement(final String uri, final String localName, final String qName,
                    final Attributes attributes)
            {
                if ("message".equals(qName))
                {
                    event.thenApplyAsync(ev ->
                    {
                        ev.setMessage(new EbayMessage());
                        return ev;
                    });
                }
            }

            @Override
            public EventFactory<EbayEvent> getEventFactory()
            {
                return EbayEvent::new;
            }
        };
    }

    @Override
    public ProcessingType getProcessingType()
    {
        return ProcessingType.ASYNC;
    }
}
