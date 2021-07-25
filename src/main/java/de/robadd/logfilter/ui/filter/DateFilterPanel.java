package de.robadd.logfilter.ui.filter;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import de.robadd.logfilter.Translator;
import de.robadd.logfilter.model.Event;
import de.robadd.logfilter.model.EventFilter;
import de.robadd.logfilter.model.Index;
import de.robadd.logfilter.model.TimeSpan;

public class DateFilterPanel extends FilterPanel<TimeSpan>
{
	private transient TimeSpan value;
	private transient TimeSpan valueSpan;

	public DateFilterPanel()
	{
		super();
	}

	private static final long serialVersionUID = -4111684591269597533L;
	private JSpinner fromDate;
	private JSpinner fromTime;
	private JSpinner toDate;
	private JSpinner toTime;
	private JCheckBox chckbxNewCheckBox;

	@Override
	public String getTitle()
	{
		return Translator.getCurrent().get("date");
	}

	@Override
	public List<TimeSpan> getSelectedValues()
	{
		return Arrays.asList(value);
	}

	@Override
	public void setValues(final List<TimeSpan> values)
	{
		value = values.get(0);
	}

	@Override
	protected void init()
	{
		valueSpan = new TimeSpan(new Date(), new Date());

		setLayout(new FormLayout(new ColumnSpec[]
		{ FormSpecs.UNRELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode(
			"default:grow"), FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.UNRELATED_GAP_COLSPEC, }, new RowSpec[]
		{ FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.MIN_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.MIN_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, }));

		chckbxNewCheckBox = new JCheckBox("active");
		add(chckbxNewCheckBox, "4, 2");

		JLabel fromLabel = new JLabel(Translator.getCurrent().get("from"));
		add(fromLabel, "2, 4");
		JLabel toLabel = new JLabel(Translator.getCurrent().get("to"));
		add(toLabel, "2, 6");

		fromDate = new JSpinner(new SpinnerDateModel());
		fromTime = new JSpinner(new SpinnerDateModel());
		toDate = new JSpinner(new SpinnerDateModel());
		toTime = new JSpinner(new SpinnerDateModel());

		createInputfield("dd.MM.yyyy", "4, 4", valueSpan.getFrom(), fromDate);
		createInputfield("HH:mm:ss", "6, 4", valueSpan.getFrom(), fromTime);

		createInputfield("dd.MM.yyyy", "4, 6", valueSpan.getFrom(), toDate);
		createInputfield("HH:mm:ss", "6, 6", valueSpan.getFrom(), toTime);
	}

	private void createInputfield(final String dateFormat, final String layout, final Date date, final JSpinner element)
	{
		JSpinner.DateEditor dateEditorFrom = new JSpinner.DateEditor(element, dateFormat);
		element.setEditor(dateEditorFrom);
		element.setValue(date);
		add(element, layout);
	}

	@Override
	public void setValuesFromIndex(final Index index)
	{
		valueSpan = new TimeSpan(index.getMinDate(), index.getMaxDate());
		fromTime.setValue(valueSpan.getFrom());
		toTime.setValue(valueSpan.getTo());
		fromDate.setValue(valueSpan.getFrom());
		toDate.setValue(valueSpan.getTo());
	}

	@Override
	public boolean isImplemented()
	{
		return true;
	}

	@SuppressWarnings("deprecation") // Will be updated in future revision
	@Override
	public final EventFilter<Event, TimeSpan> getEventFilter()
	{
		if (!chckbxNewCheckBox.getModel().isEnabled())
		{
			return EventFilter.getTrueTester();
		}
		EventFilter<Event, TimeSpan> retVal = new EventFilter<>();

		Date fromT = (Date) fromTime.getValue();
		Date toT = (Date) toTime.getValue();
		Date fromD = (Date) fromDate.getValue();
		Date toD = (Date) toDate.getValue();
		Date from = new Date(fromD.getYear(), fromD.getMonth(), fromD.getDate(), fromT.getHours(), fromD.getMinutes(),
				fromD.getSeconds());
		Date to = new Date(toD.getYear(), toD.getMonth(), toD.getDate(), toT.getHours(), toD.getMinutes(), toD
				.getSeconds());

		value = new TimeSpan(from, to);
		retVal.addPredicate(a -> value.contains(a.getTimestamp()));
		return retVal;
	}

	protected JCheckBox getChckbxNewCheckBox()
	{
		return chckbxNewCheckBox;
	}
}
