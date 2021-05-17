package de.robadd.logfilter.ui.tabs;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import de.robadd.logfilter.logtypes.LogConfiguration;
import de.robadd.logfilter.logtypes.hitmeister.HitmeisterLogConfiguration;
import de.robadd.logfilter.ui.filter.FilterPanel;

public class HitmeisterLogPanel extends LogPanel
{

	private static final long serialVersionUID = 7958264456145334945L;

	public HitmeisterLogPanel()
	{
		super();
		this.setEnabled(false);
	}

	@Override
	public String getTitle()
	{
		return "Kaufland";
	}

	@Override
	public Icon getIcon()
	{
		return new ImageIcon(ClassLoader.getSystemResource("icons/hitmeister.png"));
	}

	@Override
	protected Collection<FilterPanel<?>> getFilterPanels()
	{
		return new ArrayList<>();
	}

	@Override
	protected LogConfiguration getLogConfiguration()
	{
		return new HitmeisterLogConfiguration();
	}

	@Override
	public int priority()
	{
		return 300;
	}

}
