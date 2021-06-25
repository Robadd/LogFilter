package de.robadd.logfilter.ui.filter;

import java.util.Collection;
import java.util.stream.Collectors;

import de.robadd.logfilter.Translator;
import de.robadd.logfilter.model.Index;

public class ClassFilterPanel extends SearchableFilterPanel
{
	private static final long serialVersionUID = -4493731056085604192L;

	public ClassFilterPanel()
	{
		super();
	}

	@Override
	public String getTitle()
	{
		return Translator.getCurrent().get("class");
	}

	@Override
	public void setValuesFromIndex(final Index index)
	{
		Collection<String> classList = index.getClassList();
		setValues(classList.stream().sorted().collect(Collectors.toList()));
	}

	@Override
	public boolean isImplemented()
	{
		return true;
	}
}
