package de.robadd.logfilter.ui.filter;

import java.util.Calendar;

public class TimeSpan
{
    private Calendar from;
    private Calendar to;

    /**
     * TimeSpan
     *
     * @param from
     * @param to
     */
    public TimeSpan(final Calendar from, final Calendar to)
    {
        this.from = from;
        this.to = to;
    }

    /**
     * getFrom
     *
     * @return Calendar from
     */
    public Calendar getFrom()
    {
        return from;
    }

    /**
     * setFrom
     *
     * @param from Calendar
     */
    public void setFrom(final Calendar from)
    {
        this.from = from;
    }

    /**
     * getTo
     *
     * @return Calendar to
     */
    public Calendar getTo()
    {
        return to;
    }

    /**
     * setTo
     *
     * @param to Calendar
     */
    public void setTo(final Calendar to)
    {
        this.to = to;
    }

}
