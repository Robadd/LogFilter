package de.robadd.logfilter.ui.filter;

import java.awt.Container;
import java.lang.reflect.Method;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import de.robadd.logfilter.FilterMethod;
import de.robadd.logfilter.Translator;
import de.robadd.logfilter.model.Event;
import de.robadd.logfilter.model.EventFilter;
import de.robadd.logfilter.model.EventFilter.EventFilterBuilder;
import de.robadd.logfilter.model.Index;
import de.robadd.logfilter.ui.tabs.LogPanel;

public abstract class FilterPanel<T> extends JPanel
{
	private static final long serialVersionUID = 3914473837809874011L;

	protected FilterPanel()
	{
		super();
		setBorder(new TitledBorder(null, getTitle(), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		if (isImplemented())
		{
			init();
		}
		else
		{
			add(new JLabel(Translator.getCurrent().get("NotImplemented")));
		}
	}

	protected abstract void init();

	public abstract String getTitle();

	public abstract List<T> getSelectedValues();

	public abstract void setValues(List<T> values);

	public abstract void setValuesFromIndex(Index index);

	public abstract boolean isImplemented();

	public Method getEventMethod(final Class<?> clazz)
	{
		for (Method method : clazz.getMethods())
		{
			if (method.isAnnotationPresent(FilterMethod.class) && method.getAnnotation(FilterMethod.class).value()
					.isAssignableFrom(getClass()))
			{
				return method;
			}
		}
		return null;
	}

	public final EventFilter<Event, T> getEventFilter()
	{
		Container parent = getParent().getParent();
		if (getSelectedValues().isEmpty() || !(parent instanceof LogPanel))
		{
			return EventFilter.getTrueTester();
		}
		LogPanel castParent = (LogPanel) parent;
		Method eventMethod = getEventMethod(castParent.getLogConfiguration().getEventBuilder().build().getClass());
		if (eventMethod == null)
		{
			return EventFilter.getTrueTester();
		}
		final EventFilter<Event, T> build = new EventFilterBuilder<Event, T>().build(eventMethod);
		build.setfilterValues(getSelectedValues());
		return build;
	}
}
