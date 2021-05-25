package de.robadd.logfilter.ui.tabs;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import de.robadd.logfilter.logtypes.LogConfiguration;
import de.robadd.logfilter.logtypes.amazon.AmazonLogConfiguration;
import de.robadd.logfilter.ui.filter.FilterPanel;

/**
 * <b>Title:</b> AmazonLogPanel <br>
 * <b>Copyright:</b> Copyright (c) 2021 <br>
 * <b>Company:</b> Speed4Trade GmbH <br>
 * <b>Description:</b> <br>
 *
 * @author rok
 * @version $Id: AmazonLogPanel.java 1 25.05.2021 rok $
 */
public class AmazonLogPanel extends LogPanel
{

    private static final long serialVersionUID = -7283639866042233028L;

    /**
     * {@inheritDoc}
     *
     * @see de.robadd.logfilter.ui.tabs.LogPanel#getTitle()
     */
    @Override
    public String getTitle()
    {
        return "Amazon";
    }

    /**
     * {@inheritDoc}
     *
     * @see de.robadd.logfilter.ui.tabs.LogPanel#getIcon()
     */
    @Override
    public Icon getIcon()
    {
        return new ImageIcon(ClassLoader.getSystemResource("icons/amazon.png"));
    }

    /**
     * {@inheritDoc}
     *
     * @see de.robadd.logfilter.ui.tabs.LogPanel#getFilterPanels()
     */
    @Override
    protected Collection<FilterPanel<?>> getFilterPanels()
    {
        // TODO Auto-generated method stub
        return new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     *
     * @see de.robadd.logfilter.ui.tabs.LogPanel#getLogConfiguration()
     */
    @Override
    public LogConfiguration getLogConfiguration()
    {
        return new AmazonLogConfiguration();
    }
}
