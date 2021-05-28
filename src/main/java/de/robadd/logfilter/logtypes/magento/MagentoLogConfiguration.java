package de.robadd.logfilter.logtypes.magento;

import de.robadd.logfilter.logtypes.LogConfiguration;
import de.robadd.logfilter.model.Event;
import de.robadd.logfilter.model.EventBuilder;
import de.robadd.logfilter.model.IndexBuilder;
import de.robadd.logfilter.ui.tabs.LogPanel;
import de.robadd.logfilter.ui.tabs.MagentoLogPanel;

public class MagentoLogConfiguration implements LogConfiguration
{

	@Override
	public LogPanel getPanel()
	{
		return new MagentoLogPanel();
	}

	@Override
	public void fillElement(final Event event)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public EventBuilder getEventBuilder()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IndexBuilder getIndexBuilder()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
