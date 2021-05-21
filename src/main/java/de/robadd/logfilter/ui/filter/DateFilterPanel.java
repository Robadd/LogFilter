package de.robadd.logfilter.ui.filter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import de.robadd.logfilter.model.Index;

public class DateFilterPanel extends UnimplementedFilterPanel<TimeSpan>
{

    private JTextField fromTextField;
    private JTextField toTextField;

    public DateFilterPanel()
    {
        super();
    }

    private static final long serialVersionUID = -4111684591269597533L;

    @Override
    public String getTitle()
    {
        return "Datum";
    }

    @Override
    public List<TimeSpan> getSelectedValues()
    {
        return new ArrayList<>();
    }

    @Override
    public void setValues(final List<TimeSpan> values)
    {
        // Do nothing
    }

    @Override
    protected void init()
    {
        setLayout(new FormLayout(new ColumnSpec[]
        {FormSpecs.UNRELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode(
                "default:grow"), FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
                FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.UNRELATED_GAP_COLSPEC,}, new RowSpec[]
        {FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.MIN_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.MIN_ROWSPEC,
                FormSpecs.RELATED_GAP_ROWSPEC,}));

        JLabel fromLabel = new JLabel("Von:");
        add(fromLabel, "2, 2");

        JSlider fromTime = new JSlider();
        add(fromTime, "4, 2");

        fromTextField = new JTextField();
        add(fromTextField, "6, 2, fill, default");
        fromTextField.setColumns(10);

        JLabel ToLabel = new JLabel("Bis:");
        add(ToLabel, "2, 4");

        JSlider toTime = new JSlider();
        add(toTime, "4, 4");

        toTextField = new JTextField();
        toTextField.setColumns(10);
        add(toTextField, "6, 4, fill, default");
    }

    @Override
    public void setValuesFromIndex(final Index index)
    {
        // TODO implement
        // values = index.getLogLevels().stream().collect(Collectors.toList());
    }

    @Override
    public Method getEventMethod(final Class<?> clazz)
    {
        try
        {
            return clazz.getMethod("getTimestamp");
        }
        catch (NoSuchMethodException | SecurityException e)
        {
            return null;
        }
    }

    @Override
    public boolean isImplemented()
    {
        return false;
    }
}
