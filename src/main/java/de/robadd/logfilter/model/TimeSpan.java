package de.robadd.logfilter.model;

import java.util.Date;

public class TimeSpan
{
	private Date from;
	private Date to;

	/**
	 * TimeSpan
	 *
	 * @param from
	 * @param to
	 */
	public TimeSpan(final Date from, final Date to)
	{
		this.from = from;
		this.to = to;
	}

	public boolean contains(final Date timestamp)
	{
		return !from.after(timestamp) && !to.before(timestamp);
	}

	/**
	 * getFrom
	 *
	 * @return Calendar from
	 */
	public Date getFrom()
	{
		return from;
	}

	/**
	 * setFrom
	 *
	 * @param from Calendar
	 */
	public void setFrom(final Date from)
	{
		this.from = from;
	}

	/**
	 * getTo
	 *
	 * @return Calendar to
	 */
	public Date getTo()
	{
		return to;
	}

	/**
	 * setTo
	 *
	 * @param to Calendar
	 */
	public void setTo(final Date to)
	{
		this.to = to;
	}

}
