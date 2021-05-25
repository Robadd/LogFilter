package de.robadd.logfilter.logtypes.amazon;

import de.robadd.logfilter.model.Message;

/**
 * <b>Title:</b> AmazonMessage <br>
 * <b>Copyright:</b> Copyright (c) 2021 <br>
 * <b>Company:</b> Speed4Trade GmbH <br>
 * <b>Description:</b> <br>
 *
 * @author rok
 * @version $Id: AmazonMessage.java 1 25.05.2021 rok $
 */
public class AmazonMessage implements Message
{

    /**
     * {@inheritDoc}
     *
     * @see de.robadd.logfilter.model.Message#character(java.lang.String)
     */
    @Override
    public void character(String str)
    {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     *
     * @see de.robadd.logfilter.model.Message#getRequestMethod()
     */
    @Override
    public String getRequestMethod()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
