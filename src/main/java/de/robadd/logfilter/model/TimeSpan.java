package de.robadd.logfilter.model;

import java.time.LocalDateTime;

public class TimeSpan
{
	private LocalDateTime from;
	private LocalDateTime to;

	/**
	 * TimeSpan
	 *
	 * @param from
	 * @param to
	 */
	public TimeSpan(final LocalDateTime from, final LocalDateTime to)
	{
		this.from = from;
		this.to = to;
	}

	/**
	 * getFrom
	 *
	 * @return Calendar from
	 */
	public LocalDateTime getFrom()
	{
		return from;
	}

	/**
	 * setFrom
	 *
	 * @param from Calendar
	 */
	public void setFrom(final LocalDateTime from)
	{
		this.from = from;
	}

	/**
	 * getTo
	 *
	 * @return Calendar to
	 */
	public LocalDateTime getTo()
	{
		return to;
	}

	/**
	 * setTo
	 *
	 * @param to Calendar
	 */
	public void setTo(final LocalDateTime to)
	{
		this.to = to;
	}

}
