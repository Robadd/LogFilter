package de.robadd.logfilter.ui.filter;

import java.util.Arrays;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import de.robadd.logfilter.Translator;
import de.robadd.logfilter.model.Index;

public class MessageFilterPanel extends FilterPanel<String>
{

	private static final long serialVersionUID = 5997233888313336477L;
	private JTextField textField;

	@Override
	public String getTitle()
	{
		return Translator.getCurrent().get("messageContent");
	}

	@Override
	protected void init()
	{
		setLayout(new FormLayout(new ColumnSpec[]
		{ FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("424px"), FormSpecs.RELATED_GAP_COLSPEC, }, new RowSpec[]
		{ FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("20px"), FormSpecs.RELATED_GAP_ROWSPEC, }));

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		add(textField, "2, 2, fill, fill");
		textField.setColumns(10);
	}

	@Override
	public List<String> getSelectedValues()
	{
		return Arrays.asList(textField.getText());
	}

	@Override
	public void setValues(final List<String> values)
	{
		// Nothing to set
	}

	@Override
	public void setValuesFromIndex(final Index index)
	{
		// Nothing to set
	}

	@Override
	public boolean isImplemented()
	{
		return true;
	}

}
