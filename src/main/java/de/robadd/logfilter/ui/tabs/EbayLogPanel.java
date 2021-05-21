package de.robadd.logfilter.ui.tabs;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import de.robadd.logfilter.logtypes.LogConfiguration;
import de.robadd.logfilter.logtypes.ebay.EbayLogConfiguration;
import de.robadd.logfilter.ui.filter.CallTypeFilterPanel;
import de.robadd.logfilter.ui.filter.DateFilterPanel;
import de.robadd.logfilter.ui.filter.FilterPanel;

public class EbayLogPanel extends LogPanel
{
    private static final long serialVersionUID = -6055160924926935559L;

    public EbayLogPanel()
    {
        init();
    }

    @Override
    public String getTitle()
    {
        return "eBay";
    }

    @Override
    public Icon getIcon()
    {
        return new ImageIcon(ClassLoader.getSystemResource("icons/ebay.png"));
    }

    @Override
    protected Collection<FilterPanel<?>> getFilterPanels()
    {
        Collection<FilterPanel<?>> retVal = new ArrayList<>();
        DateFilterPanel dateFilter = new DateFilterPanel();
        CallTypeFilterPanel callTypeFilterPanel = new CallTypeFilterPanel();
        retVal.add(dateFilter);
        retVal.add(callTypeFilterPanel);
        return retVal;
    }

    @Override
    public LogConfiguration getLogConfiguration()
    {
        return new EbayLogConfiguration();
    }

    @Override
    public boolean isImplemented()
    {
        return true;
    }
}
