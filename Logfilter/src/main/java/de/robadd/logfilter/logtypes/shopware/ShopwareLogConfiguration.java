package de.robadd.logfilter.logtypes.shopware;

import org.xml.sax.Attributes;

import de.robadd.logfilter.logtypes.LogConfiguration;
import de.robadd.logfilter.logtypes.ProcessingType;
import de.robadd.logfilter.model.EventBuilder;
import de.robadd.logfilter.model.EventFactory;
import de.robadd.logfilter.model.IndexBuilder;
import de.robadd.logfilter.model.SynchronousEventBuilder;

public class ShopwareLogConfiguration implements LogConfiguration<ShopwareEvent>
{

    @Override
    public EventFactory<ShopwareEvent> getEventFactory()
    {
        return ShopwareEvent::new;
    }

    @Override
    public IndexBuilder getIndexBuilder()
    {
        return ShopwareLogIndex::new;
    }

    @Override
    public EventBuilder<ShopwareEvent> getEventBuilder()
    {
        return new SynchronousEventBuilder<ShopwareEvent>()
        {

            @Override
            public void fillElement(final String uri, final String localName, final String qName,
                    final Attributes attributes)
            {
                if ("message".equals(qName))
                {
                    event.setMessage(new ShopwareMessage());
                }
            }

            @Override
            public EventFactory<ShopwareEvent> getEventFactory()
            {
                return ShopwareEvent::new;
            }
        };
    }

    @Override
    public ProcessingType getProcessingType()
    {
        return ProcessingType.SYNC;
    }

}
