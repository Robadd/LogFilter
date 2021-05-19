package de.robadd.logfilter.logtypes.shopware;

import java.lang.reflect.Method;

import de.robadd.logfilter.model.Event;

public class ShopwareEvent extends Event
{
	public String getRequestMethod()
	{
		return message.getRequestMethod();
	}

	@Override
	public Method getCustomMethod(final String methodName)
	{
		try
		{
			return this.getClass().getMethod(methodName);
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
