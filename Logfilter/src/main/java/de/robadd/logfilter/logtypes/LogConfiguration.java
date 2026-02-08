package de.robadd.logfilter.logtypes;

import de.robadd.logfilter.model.Event;
import de.robadd.logfilter.model.EventBuilder;
import de.robadd.logfilter.model.EventFactory;
import de.robadd.logfilter.model.IndexBuilder;

public interface LogConfiguration<T extends Event>
{
    public EventFactory<T> getEventFactory();

    public EventBuilder<T> getEventBuilder();

    public IndexBuilder getIndexBuilder();

    ProcessingType getProcessingType();
}
