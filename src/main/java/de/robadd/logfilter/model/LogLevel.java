package de.robadd.logfilter.model;

public enum LogLevel
{
	TRACE, DEBUG, INFO, WARN, ERROR;

	public static LogLevel getByValue(final String val)
	{
		switch (val)
		{
			case "ERROR":
				return ERROR;
			case "WARN":
				return WARN;
			case "INFO":
				return INFO;
			case "DEBUG":
				return DEBUG;
			case "TRACE":
				return TRACE;
			default:
				return null;
		}
	}

	@Override
	public String toString()
	{
		return this.name();
	}
}
