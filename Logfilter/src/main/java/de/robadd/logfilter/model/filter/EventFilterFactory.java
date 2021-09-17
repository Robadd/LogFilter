package de.robadd.logfilter.model.filter;

import java.util.ArrayList;
import java.util.Collection;

import de.robadd.logfilter.model.Event;
import de.robadd.logfilter.model.EventFilter;

public class EventFilterFactory<T extends Event>
{
	private Collection<EventFilter<T, ?>> filters = new ArrayList<>();

	public boolean test(final T event)
	{
		return filters.stream().allMatch(a -> a.test(event));
	}

	public void addFilter(final EventFilter<T, ?> filter)
	{
		filters.add(filter);
	}
}
