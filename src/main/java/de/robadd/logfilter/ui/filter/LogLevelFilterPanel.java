package de.robadd.logfilter.ui.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.UIManager;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import de.robadd.logfilter.Translator;
import de.robadd.logfilter.model.Index;
import de.robadd.logfilter.model.LogLevel;

public class LogLevelFilterPanel extends FilterPanel<LogLevel>
{
	public LogLevelFilterPanel()
	{
	}

	private List<LogLevel> values = new ArrayList<>();
	private JList<LogLevel> list;

	@Override
	protected void init()
	{
		setLayout(new FormLayout(new ColumnSpec[]
		{ FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("438px:grow"), FormSpecs.RELATED_GAP_COLSPEC, }, new RowSpec[]
		{ FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.MIN_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, }));
		list = new JList<>();
		list.setBorder(UIManager.getBorder("ComboBox.border"));
		add(list, "2, 2, fill, fill");
	}

	private static final long serialVersionUID = -485708119352809977L;

	@Override
	public String getTitle()
	{
		return Translator.getCurrent().get("logLevel");
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
	public boolean isImplemented()
	{
		return true;
	}
}
