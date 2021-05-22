package de.robadd.logfilter.logtypes.ebay;

import static java.lang.Math.min;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jgoodies.common.base.Strings;

import de.robadd.logfilter.model.Message;

public class EbayMessage implements Message
{
	private String requestMethod;
	private String msg;

	@Override
	public void character(final String str)
	{
		setRequestMethod(str);
	}

	private void setRequestMethod(final String strInput)
	{
		if (Strings.isBlank(strInput))
		{
			return;
		}
		msg = strInput;
		final String str = strInput.substring(0, min(500, strInput.length()));
		if (!str.contains("Request") && !str.contains("Response"))
		{
			return;
		}
		Pattern pattern = Pattern.compile("([a-zA-Z]*)(Request|Response)");
		Matcher matcherSecond = pattern.matcher(str);
		if (matcherSecond.find())
		{
			requestMethod = matcherSecond.group(0);
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
