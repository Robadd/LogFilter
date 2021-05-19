package de.robadd.logfilter.ui.filter;

import java.awt.GridLayout;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.UIManager;

import de.robadd.logfilter.model.Index;
import de.robadd.logfilter.model.LogLevel;

public class LogLevelFilterPanel extends FilterPanel<LogLevel>
{
	private List<LogLevel> values = new ArrayList<>();
	private List<LogLevel> selectedValues = new ArrayList<>();
	private JList<LogLevel> list;

	@Override
	protected void init()
	{
		list = new JList<>();
		setLayout(new GridLayout(0, 1, 0, 0));
		list.setBorder(UIManager.getBorder("ComboBox.border"));
		add(list);
	}

	private static final long serialVersionUID = -485708119352809977L;

	@Override
	public String getTitle()
	{
		return "Log Level";
	}

	@Override
	public List<LogLevel> getSelectedValues()
	{
		return list.getSelectedValuesList();
	}

	@Override
	public void setValues(final List<LogLevel> argValues)
	{
		values = argValues;
		list.setModel(new AbstractListModel<LogLevel>()
		{
			private static final long serialVersionUID = -1834746057622280163L;

			@Override
			public LogLevel getElementAt(final int index)
			{
				return values.get(index);
			}

			@Override
			public int getSize()
			{
				return values.size();
			}
		});
		list.setEnabled(true);
	}

	@Override
	public void setValuesFromIndex(final Index index)
	{
		values = index.getLogLevels().stream().sorted().collect(Collectors.toList());
		setValues(values);
	}

	@Override
	public Method getEventMethod(final Class<?> clazz)
	{
		try
		{
			return clazz.getMethod("getLevel");
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			return null;
		}
	}

	@Override
	public boolean isImplemented()
	{
		return true;
	}
}
