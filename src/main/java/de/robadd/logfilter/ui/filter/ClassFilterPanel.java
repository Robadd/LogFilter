package de.robadd.logfilter.ui.filter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import de.robadd.logfilter.Translator;
import de.robadd.logfilter.model.Index;

public class ClassFilterPanel extends FilterPanel<String>
{
	private static final long serialVersionUID = -4493731056085604192L;
	private JList<String> list;
	private List<String> values = new ArrayList<>();
	private JTextField filteringInputField;
	private transient Predicate<String> listFilter = input -> input.matches(filteringInputField.getText());

	public ClassFilterPanel()
	{
		super();
	}

	@Override
	protected void init()
	{
		setLayout(new FormLayout(new ColumnSpec[]
		{ FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC, },
				new RowSpec[]
				{ FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.MIN_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode(
					"pref:grow"), FormSpecs.RELATED_GAP_ROWSPEC, }));
		JPanel panel = new JPanel();
		add(panel, "2, 2, fill, center");
		panel.setLayout(new FormLayout(new ColumnSpec[]
		{ ColumnSpec.decode("default:grow"), FormSpecs.LABEL_COMPONENT_GAP_COLSPEC, FormSpecs.MIN_COLSPEC,
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC, FormSpecs.MIN_COLSPEC, }, new RowSpec[]
		{ RowSpec.decode("23px"), }));

		filteringInputField = new JTextField();
		panel.add(filteringInputField, "1, 1, fill, center");
		filteringInputField.addKeyListener(getSearchKeyListener());

		JButton selectAll = new JButton(Translator.getCurrent().get("SelectAll"));
		panel.add(selectAll, "3, 1, left, top");
		selectAll.addActionListener(e -> list.setSelectionInterval(0, list.getModel().getSize() - 1));

		JButton selectNone = new JButton(Translator.getCurrent().get("SelectNone"));
		panel.add(selectNone, "5, 1");
		selectNone.addActionListener(e -> list.setSelectedIndices(new int[0]));
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "2, 4, fill, fill");

		list = new JList<>();
		scrollPane.setViewportView(list);
		list.setModel(getListModel());
	}

	private KeyListener getSearchKeyListener()
	{
		return new KeyListener()
		{
			@Override
			public void keyTyped(final KeyEvent e)
			{
				listFilter = (final String input) -> input.contains(filteringInputField.getText());
				list.setModel(getListModel());
			}

			@Override
			public void keyReleased(final KeyEvent e)
			{
				// Not needed
			}

			@Override
			public void keyPressed(final KeyEvent e)
			{
				// Not needed
			}
		};
	}

	private AbstractListModel<String> getListModel()
	{
		return new AbstractListModel<String>()
		{
			private static final long serialVersionUID = -5488028287436447719L;

			@Override
			public int getSize()
			{
				return (int) values.stream().filter(listFilter).count();
			}

			@Override
			public String getElementAt(final int index)
			{
				return values.stream().filter(listFilter).collect(Collectors.toList()).get(index);
			}
		};
	}

	@Override
	public String getTitle()
	{
		return Translator.getCurrent().get("class");
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
		list.setEnabled(true);
	}

	public JList<String> getList()
	{
		return list;
	}

	@Override
	public void setValuesFromIndex(final Index index)
	{
		Collection<String> classList = index.getClassList();
		setValues(classList.stream().sorted().collect(Collectors.toList()));
	}

	@Override
	public Method getEventMethod(final Class<?> clazz)
	{
		try
		{
			return clazz.getMethod("getClazz");
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
