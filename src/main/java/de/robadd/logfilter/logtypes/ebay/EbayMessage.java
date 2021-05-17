package de.robadd.logfilter.logtypes.ebay;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.robadd.logfilter.model.Message;

public class EbayMessage implements Message
{
	private String requestMethod;
	private String msg;

	@Override
	public void character(final String str)
	{
		if (!str.isBlank())
		{
			msg = str;
			Pattern pattern = Pattern.compile("([a-zA-Z]*)(Request|Response)");
			Matcher matcher = pattern.matcher(str);
			if (matcher.find())
			{
				requestMethod = matcher.group(0);
			}
		}
	}

	@Override
	public String getRequestMethod()
	{
		return requestMethod;
	}

	@Override
	public String toString()
	{
		return msg;
	}
}
