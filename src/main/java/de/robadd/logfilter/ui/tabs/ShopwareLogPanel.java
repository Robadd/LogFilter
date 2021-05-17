package de.robadd.logfilter.ui.tabs;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import de.robadd.logfilter.logtypes.LogConfiguration;
import de.robadd.logfilter.logtypes.shopware.ShopwareLogConfiguration;
import de.robadd.logfilter.ui.filter.CallTypeFilterPanel;
import de.robadd.logfilter.ui.filter.DateFilterPanel;
import de.robadd.logfilter.ui.filter.FilterPanel;

public class ShopwareLogPanel extends LogPanel
{
	private static final long serialVersionUID = 182081076221645246L;

	public ShopwareLogPanel()
	{
		init();
	}

	@Override
	public String getTitle()
	{
		return "shopware";
	}

	@Override
	public Icon getIcon()
	{
		return new ImageIcon(ClassLoader.getSystemResource("icons/shopware.png"));
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
	protected LogConfiguration getLogConfiguration()
	{
		return new ShopwareLogConfiguration();
	}

	@Override
	public int priority()
	{
		return 400;
	}

	@Override
	public boolean isImplemented()
	{
		return true;
	}
}
