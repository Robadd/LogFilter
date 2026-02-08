package de.robadd.logfilter.model;

public interface EventFactory<T extends Event>
{
	public T build();
}
