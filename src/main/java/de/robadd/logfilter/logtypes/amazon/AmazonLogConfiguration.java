package de.robadd.logfilter.logtypes.amazon;

import org.xml.sax.Attributes;

import de.robadd.logfilter.logtypes.LogConfiguration;
import de.robadd.logfilter.model.Event;
import de.robadd.logfilter.model.EventBuilder;
import de.robadd.logfilter.model.IndexBuilder;

/**
 * <b>Title:</b> AmazonLogConfiguration <br>
 * <b>Copyright:</b> Copyright (c) 2021 <br>
 * <b>Company:</b> Speed4Trade GmbH <br>
 * <b>Description:</b> <br>
 *
 * @author rok
 * @version $Id: AmazonLogConfiguration.java 1 25.05.2021 rok $
 */
public class AmazonLogConfiguration implements LogConfiguration
{

	/**
	 * {@inheritDoc}
	 *
	 * @see de.robadd.logfilter.logtypes.LogConfiguration#fillElement(de.robadd.logfilter.model.Event,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      org.xml.sax.Attributes)
	 */
	@Override
	public void fillElement(final Event event, final String uri, final String localName, final String qName,
			final Attributes attributes)
	{
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 *
	 * @see de.robadd.logfilter.logtypes.LogConfiguration#getEventBuilder()
	 */
	@Override
	public EventBuilder getEventBuilder()
	{
		return AmazonEvent::new;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see de.robadd.logfilter.logtypes.LogConfiguration#getIndexBuilder()
	 */
	@Override
	public IndexBuilder getIndexBuilder()
	{
		return AmazonLogIndex::new;
	}

}
