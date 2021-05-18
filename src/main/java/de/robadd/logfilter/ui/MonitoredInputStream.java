package de.robadd.logfilter.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JProgressBar;

public class MonitoredInputStream extends FileInputStream
{
	private volatile long mark = 0;
	private volatile long lastTriggeredLocation = 0;
	private volatile long location = 0;
	private long totalSize;
	private int scalingFactor;
	private JProgressBar progressBar;
	private final int threshold = 1024;

	public MonitoredInputStream(final File file, final JProgressBar argProgressBar) throws FileNotFoundException
	{
		super(file);
		scalingFactor = 1;
		totalSize = file.length();
		progressBar = argProgressBar;
		while (totalSize > Integer.MAX_VALUE)
		{
			totalSize /= 1024;
			scalingFactor *= 1024;
		}
		progressBar.setMaximum(Math.toIntExact(totalSize));
		progressBar.setMinimum(0);
		progressBar.setValue(0);
		progressBar.setString("");
	}

	private int getScaledProgress()
	{
		final long scaledLocation = location / scalingFactor;

		return Math.toIntExact(scaledLocation);
	}

	protected void triggerChanged(final long location)
	{
		if (threshold > 0 && Math.abs(location - lastTriggeredLocation) < threshold)
		{
			return;
		}
		lastTriggeredLocation = location;

		progressBar.setValue(getScaledProgress());

	}

	@Override
	public int read() throws IOException
	{
		final int i = super.read();
		if (i != -1)
		{
			triggerChanged(location++);
		}
		return i;
	}

	@Override
	public int read(final byte[] b, final int off, final int len) throws IOException
	{
		final int i = super.read(b, off, len);
		if (i > 0)
		{
			triggerChanged(location += i);
		}
		return i;
	}

	@Override
	public long skip(final long n) throws IOException
	{
		final long i = super.skip(n);
		if (i > 0)
		{
			triggerChanged(location += i);
		}
		return i;
	}

	@Override
	public void mark(final int readlimit)
	{
		super.mark(readlimit);
		mark = location;
	}

	@Override
	public void reset() throws IOException
	{
		super.reset();
		if (location != mark)
		{
			triggerChanged(location = mark);
		}
	}
}
