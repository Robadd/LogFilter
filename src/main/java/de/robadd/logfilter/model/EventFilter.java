package de.robadd.logfilter.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

/**
 * @author Robert Kraus
 * @param <T> The Event type to filter against
 * @param <S> the ValueType to filter against
 */
public class EventFilter<T extends Event, S>
{
	Collection<Predicate<Event>> predicates = new ArrayList<>();
	Collection<S> filterValues = new ArrayList<>();

	public static <R extends Event, U> EventFilter<R, U> getTrueTester()
	{
		return new EventFilter<R, U>()
		{
			@Override
			public boolean test(final R event)
			{
				return true;
			}
		};
	}

	private void setTestMethod(final Method getter)
	{
		addPredicate(new Predicate<Event>()
		{
			@Override
			public boolean test(final Event t)
			{
				try
				{
					if (getter == null)
					{
						return true;
					}
					@SuppressWarnings("unchecked")
					S val = (S) getter.invoke(t);
					return filterValues.stream().anyMatch(a -> a.equals(val));
				}
				catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
				{
					e.printStackTrace();
					return false;
				}
			}
		});
	}

	private void addPredicate(final Predicate<Event> predicate)
	{
		predicates.add(predicate);
	}

	public boolean test(final T event)
	{
		return predicates.stream().allMatch(a -> a.test(event));
	}

	public void addfilterValues(final S val)
	{
		filterValues.add(val);
	}

	public void setfilterValues(final Collection<S> val)
	{
		filterValues = val;
	}

	public static class EventFilterBuilder<T extends Event, S>
	{
		public EventFilter<T, S> build(final Method getter)
		{
			EventFilter<T, S> retVal = new EventFilter<>();
			retVal.setTestMethod(getter);
			return retVal;
		}
	}
}
