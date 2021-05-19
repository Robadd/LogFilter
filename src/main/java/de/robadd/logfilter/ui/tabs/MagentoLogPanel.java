package de.robadd.logfilter.ui.tabs;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import de.robadd.logfilter.logtypes.LogConfiguration;
import de.robadd.logfilter.logtypes.magento.MagentoLogConfiguration;
import de.robadd.logfilter.ui.filter.FilterPanel;

public class MagentoLogPanel extends LogPanel
{
	private static final long serialVersionUID = 6184693472578109504L;

	@Override
	public String getTitle()
	{
		return "Magento";
	}

	@Override
	public Icon getIcon()
	{
		return new ImageIcon(ClassLoader.getSystemResource("icons/magento.png"));
	}

	@Override
	protected Collection<FilterPanel<?>> getFilterPanels()
	{
		return new ArrayList<>();
	}

	@Override
	public LogConfiguration getLogConfiguration()
	{
		return new MagentoLogConfiguration();
	}

	@Override
	public int priority()
	{
		return 200;
	}
}
