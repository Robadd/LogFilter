package de.robadd.logfilter.model;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import de.robadd.logfilter.FilterMethod;
import de.robadd.logfilter.ui.filter.ClassFilterPanel;
import de.robadd.logfilter.ui.filter.DateFilterPanel;
import de.robadd.logfilter.ui.filter.LogLevelFilterPanel;
import de.robadd.logfilter.ui.filter.ThreadFilterPanel;

public class Event
{
	private String logger;
	private LocalDateTime timestamp;
	private LogLevel level;
	private String thread;
	private String clazz;
	private String method;
	private String file;
	private Integer line;
	private String throwable;
	protected Message message;

	/**
	 * @return the timestamp
	 */
	@FilterMethod(DateFilterPanel.class)
	public LocalDateTime getTimestamp()
	{
		return timestamp;
	}

	/**
	 * @return the clazz
	 */
	@FilterMethod(ClassFilterPanel.class)
	public String getClazz()
	{
		return clazz;
	}

	/**
	 * @return the thread
	 */
	@FilterMethod(ThreadFilterPanel.class)
	public String getThread()
	{
		return thread;
	}

	/**
	 * @return the level
	 */
	@FilterMethod(LogLevelFilterPanel.class)
	public LogLevel getLevel()
	{
		return level;
	}

	/**
	 * @return the logger
	 */
	public String getLogger()
	{
		return logger;
	}

	/**
	 * @param logger the logger to set
	 */
	public void setLogger(final String logger)
	{
		this.logger = logger;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(final LocalDateTime timestamp)
	{
		this.timestamp = timestamp;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(final LogLevel level)
	{
		this.level = level;
	}

	/**
	 * @param thread the thread to set
	 */
	public void setThread(final String thread)
	{
		this.thread = thread;
	}

	/**
	 * @param clazz the clazz to set
	 */
	public void setClazz(final String clazz)
	{
		this.clazz = clazz;
	}

	/**
	 * @return the method
	 */
	public String getMethod()
	{
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(final String method)
	{
		this.method = method;
	}

	/**
	 * @return the file
	 */
	public String getFile()
	{
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(final String file)
	{
		this.file = file;
	}

	/**
	 * @return the line
	 */
	public Integer getLine()
	{
		return line;
	}

	/**
	 * @param line the line to set
	 */
	public void setLine(final Integer line)
	{
		this.line = line;
	}

	/**
	 * @return the message
	 */
	public Message getMessage()
	{
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(final Message message)
	{
		this.message = message;
	}

	public void messageCharacter(final char[] ch, final int start, final int length)
	{
		if (message != null)
		{
			String str = String.valueOf(ch, start, length);
			message.character(str);
		}
	}

	/**
	 * getThrowable
	 *
	 * @return String throwable
	 */
	public String getThrowable()
	{
		return throwable;
	}

	/**
	 * setThrowable
	 *
	 * @param throwable String
	 */
	public void setThrowable(final String throwable)
	{
		this.throwable = throwable;
	}

	public String getXML()
	{
		return MessageFormat.format(
			"<event logger=\"{0}\" timestamp=\"{1}\" level=\"{2}\" thread=\"{3}\" >\r\n<message><![CDATA[{4}]]>\r\n</message>"
					+ "\r\n<throwable>{9}</throwable><locationInfo class=\"{5}\" method=\"{6}\" file=\"{7}\" line=\"{8}\"/>\r\n</event>",
			getLogger(), DateTimeFormatter.ofPattern("dd.MM.yyyy H:mm:ss", Locale.GERMAN).format(getTimestamp()),
			getLevel(), getThread(), getMessage().toString().trim(), getClazz(), getMethod(), getFile(), getLine(),
			getThrowable());
	}
}
