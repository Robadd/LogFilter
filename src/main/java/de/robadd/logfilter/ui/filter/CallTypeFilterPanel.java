package de.robadd.logfilter.ui.filter;

import java.util.Collection;
import java.util.stream.Collectors;

import de.robadd.logfilter.Translator;
import de.robadd.logfilter.logtypes.ebay.EbayLogIndex;
import de.robadd.logfilter.logtypes.shopware.ShopwareLogIndex;
import de.robadd.logfilter.model.Index;

public class CallTypeFilterPanel extends SearchableFilterPanel
{

	private static final long serialVersionUID = 7450798551103817546L;

	public CallTypeFilterPanel()
	{
		super();
	}

	@Override
	public String getTitle()
	{
		return Translator.getCurrent().get("callType");
	}

	@Override
	public void setValuesFromIndex(final Index index)
	{
		if (index instanceof EbayLogIndex)
		{
			EbayLogIndex ind = (EbayLogIndex) index;

			Collection<String> classList = ind.getCallTypes();
			setValues(classList.stream().sorted().collect(Collectors.toList()));
		}
		else if (index instanceof ShopwareLogIndex)
		{
			ShopwareLogIndex ind = (ShopwareLogIndex) index;

			Collection<String> classList = ind.getCallTypes();
			setValues(classList.stream().sorted().collect(Collectors.toList()));
		}
	}

	@Override
	public boolean isImplemented()
	{
		return true;
	}
}
