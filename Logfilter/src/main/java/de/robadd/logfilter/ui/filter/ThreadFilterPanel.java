package de.robadd.logfilter.ui.filter;

import java.util.Collection;
import java.util.stream.Collectors;

import de.robadd.logfilter.Translator;
import de.robadd.logfilter.model.Index;

public class ThreadFilterPanel extends SearchableFilterPanel
{
	private static final long serialVersionUID = -997177975458025058L;

	public ThreadFilterPanel()
	{
		super();
	}

	@Override
	public String getTitle()
	{
		return Translator.getCurrent().get("Thread");
	}

	@Override
	public void setValuesFromIndex(final Index index)
	{
		Collection<String> threadList = index.getThreads();
		setValues(threadList.stream().sorted().collect(Collectors.toList()));
	}

	@Override
	public boolean isImplemented()
	{
		return true;
	}

}
