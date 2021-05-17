package de.robadd.logfilter.ui.filter;

import java.lang.reflect.Method;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import de.robadd.logfilter.model.Event;
import de.robadd.logfilter.model.EventFilter;
import de.robadd.logfilter.model.EventFilter.EventFilterBuilder;
import de.robadd.logfilter.model.Index;

public abstract class FilterPanel<T> extends JPanel
{

	public FilterPanel()
	{
		super();
		setBorder(new TitledBorder(null, getTitle(), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		if (isImplemented())
		{
			init();
		}
		else
		{
			add(new JLabel("Not implemented yet"));
		}
	}

	protected abstract void init();

	private static final long serialVersionUID = 3914473837809874011L;

	public abstract String getTitle();

	public abstract List<T> getSelectedValues();

	public abstract void setValues(List<T> values);

	public abstract void setValuesFromIndex(Index index);

	public abstract Method getEventMethod();

	public final EventFilter<Event, T> getEventFilter()
	{
		if (getSelectedValues().isEmpty())
		{
			return EventFilter.getTrueTester();
		}
		final EventFilter<Event, T> build = new EventFilterBuilder<Event, T>().build(getEventMethod());
		build.setfilterValues(getSelectedValues());
		return build;
	}

	public abstract boolean isImplemented();
}
