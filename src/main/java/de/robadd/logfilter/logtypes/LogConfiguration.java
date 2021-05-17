package de.robadd.logfilter.logtypes;

import org.xml.sax.Attributes;

import de.robadd.logfilter.model.Event;
import de.robadd.logfilter.model.EventBuilder;
import de.robadd.logfilter.model.IndexBuilder;
import de.robadd.logfilter.ui.tabs.LogPanel;

public interface LogConfiguration
{
	public LogPanel getPanel();

	public void fillElement(final Event event, final String uri, final String localName, final String qName,
			final Attributes attributes);

	public EventBuilder getEventBuilder();

	public IndexBuilder getIndexBuilder();
}
