package de.robadd.logfilter.ui.filter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import de.robadd.logfilter.model.Index;

public abstract class UnimplementedFilterPanel<T> extends FilterPanel<T>
{
	private static final long serialVersionUID = -1216807599119427483L;

	@Override
	public List<T> getSelectedValues()
	{
		return new ArrayList<>();
	}

	@Override
	public void setValues(final List<T> values)
	{
	}

	@Override
	public void setValuesFromIndex(final Index index)
	{
	}

	@Override
	public Method getEventMethod(final Class<?> clazz)
	{
		return null;
	}

	@Override
	public boolean isImplemented()
	{
		return false;
	}
}
