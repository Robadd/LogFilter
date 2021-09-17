package de.robadd.logfilter.model;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public abstract class Index
{
	private Set<String> classes = new HashSet<>();
	private Set<LogLevel> logLevels = new HashSet<>();
	private Set<String> threads = new HashSet<>();
	protected Date minDate;
	protected Date maxDate;
	private Integer eventCount = 0;

	public void addEvent(final Event msg)
	{
		setMinDate(msg.getTimestamp());
		setMaxDate(msg.getTimestamp());
		addClass(msg.getClazz());
		addLogLevel(msg.getLevel());
		addThread(msg.getThread());
		addLogTypeSpecifics(msg);
		eventCount++;
	}

	private void addThread(final String thread)
	{
		threads.add(thread);
	}

	public Integer getEventCount()
	{
		return eventCount;
	}

	public void clearIndex()
	{
		classes.clear();
	}

	public Collection<String> getClassList()
	{
		return classes;
	}

	/**
	 * @return the logLevels
	 */
	public Collection<LogLevel> getLogLevels()
	{
		return logLevels;
	}

	public void setClassList(final Set<String> classes)
	{
		this.classes = classes;
	}

	private void addClass(final String clazz)
	{
		classes.add(clazz);
	}

	private void addLogLevel(final LogLevel logLevel)
	{
		logLevels.add(logLevel);
	}

	/**
	 * @param minDate the minDate to set
	 */
	private void setMinDate(final Date minDate)
	{
		if (this.minDate != null && this.minDate.before(minDate))
		{
			return;
		}
		this.minDate = minDate;
	}

	/**
	 * @param maxDate the maxDate to set
	 */
	private void setMaxDate(final Date maxDate)
	{
		if (this.maxDate != null && this.maxDate.after(maxDate))
		{
			return;
		}
		this.maxDate = maxDate;
	}

	/**
	 * @return the minDate
	 */
	public Date getMinDate()
	{
		return minDate;
	}

	/**
	 * @return the maxDate
	 */
	public Date getMaxDate()
	{
		return maxDate;
	}

	public TimeSpan getTimeSpan()
	{
		return new TimeSpan(minDate, maxDate);
	}

	public abstract void addLogTypeSpecifics(Event msg);

	public Set<String> getThreads()
	{
		return threads;
	}
}
