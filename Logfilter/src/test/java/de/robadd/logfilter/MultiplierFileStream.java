package de.robadd.logfilter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MultiplierFileStream extends FileInputStream
{
	final int mult;
	int count = 0;
	int previousLength;

	public MultiplierFileStream(final File file, final int multiply) throws FileNotFoundException
	{
		super(file);
		mult = multiply;
	}

	@Override
	public int read() throws IOException
	{
		// TODO Auto-generated method stub
		return super.read();
	}

	@Override
	public int read(final byte[] b) throws IOException
	{
		// TODO Auto-generated method stub
		return super.read(b);
	}

	@Override
	public int read(final byte[] b, final int off, final int len) throws IOException
	{
		int i = super.read(b, off, len);
		if (i != -1)
		{
			previousLength = i;
		}
		if (i == -1 && count < mult)
		{
			count++;
			return previousLength;
		}
		return i;
	}
}
