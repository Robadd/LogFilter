package de.robadd.logfilter.ui.filter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import de.robadd.logfilter.logtypes.ebay.EbayLogIndex;
import de.robadd.logfilter.logtypes.shopware.ShopwareLogIndex;
import de.robadd.logfilter.model.Event;
import de.robadd.logfilter.model.Index;

public class CallTypeFilterPanel extends FilterPanel<String>
{
	private static final long serialVersionUID = 7450798551103817546L;
	private JList<String> list;
	private List<String> values = new ArrayList<>();

	public CallTypeFilterPanel()
	{
		super();
	}

	@Override
	protected void init()
	{
		setLayout(new FormLayout(new ColumnSpec[]
		{ FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), }, new RowSpec[]
		{ FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode(
			"default:grow"), }));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "2, 2, fill, fill");

		list = new JList<>();
		scrollPane.setViewportView(list);
		list.setModel(getListModel());
	}

	private AbstractListModel<String> getListModel()
	{
		return new AbstractListModel<String>()
		{
			private static final long serialVersionUID = -5488028287436447719L;

			@Override
			public int getSize()
			{
				return values.size();
			}

			@Override
			public String getElementAt(final int index)
			{
				return values.get(index);
			}
		};
	}

	@Override
	public String getTitle()
	{
		return "Call Typ";
	}

	@Override
	public List<String> getSelectedValues()
	{
		return list.getSelectedValuesList();
	}

	@Override
	public void setValues(final List<String> argValues)
	{
		values = argValues;
		list.setModel(new AbstractListModel<String>()
		{
			private static final long serialVersionUID = -1834746057622280163L;

			@Override
			public String getElementAt(final int index)
			{
				return values.get(index);
			}

			@Override
			public int getSize()
			{
				return values.size();
			}
		});
		// list.setModel(getListModel());
		list.setEnabled(true);
	}

	@Override
	public void setValuesFromIndex(final Index index)
	{
		if (index instanceof EbayLogIndex)
		{
			EbayLogIndex ind = (EbayLogIndex) index;

			Collection<String> classList = ind.getCallTypes();
			setValues(classList.stream().sorted().collect(Collectors.toList()));
		}
		else if (index instanceof ShopwareLogIndex)
		{
			ShopwareLogIndex ind = (ShopwareLogIndex) index;

			Collection<String> classList = ind.getCallTypes();
			setValues(classList.stream().sorted().collect(Collectors.toList()));
		}
	}

	@Override
	public Method getEventMethod()
	{
		try
		{
			return Event.class.getMethod("getRequestMethod");
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean isImplemented()
	{
		return true;
	}
}
