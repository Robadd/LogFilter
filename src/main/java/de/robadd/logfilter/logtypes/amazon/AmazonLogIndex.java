package de.robadd.logfilter.logtypes.amazon;

import de.robadd.logfilter.model.Event;
import de.robadd.logfilter.model.Index;

/**
 * <b>Title:</b> AmazonLogIndex <br>
 * <b>Copyright:</b> Copyright (c) 2021 <br>
 * <b>Company:</b> Speed4Trade GmbH <br>
 * <b>Description:</b> <br>
 *
 * @author rok
 * @version $Id: AmazonLogIndex.java 1 25.05.2021 rok $
 */
public class AmazonLogIndex extends Index
{

    /**
     * {@inheritDoc}
     *
     * @see de.robadd.logfilter.model.Index#addLogTypeSpecifics(de.robadd.logfilter.model.Event)
     */
    @Override
    public void addLogTypeSpecifics(final Event msg)
    {
        // TODO Auto-generated method stub

    }
}
