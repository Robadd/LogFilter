package de.robadd.logfilter;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import de.robadd.logfilter.ui.tabs.EbayLogPanel;
import de.robadd.logfilter.ui.tabs.HitmeisterLogPanel;
import de.robadd.logfilter.ui.tabs.LogPanel;
import de.robadd.logfilter.ui.tabs.MagentoLogPanel;
import de.robadd.logfilter.ui.tabs.ServerLogPanel;
import de.robadd.logfilter.ui.tabs.ShopwareLogPanel;

public class InterfaceLoader
{
	/**
	 * InterfaceLoader
	 */
	private InterfaceLoader()
	{
	}

	enum LogPanels
	{
		SERVERLOG(ServerLogPanel.class),
		EBAY(EbayLogPanel.class),
		HITMEISTER(HitmeisterLogPanel.class),
		MAGENTO(MagentoLogPanel.class),
		SHOPWARE(ShopwareLogPanel.class);

		private Class<? extends LogPanel> clazz;

		/**
		 * LogPanels
		 *
		 * @param clazz
		 */
		private LogPanels(final Class<? extends LogPanel> clazz)
		{
			this.clazz = clazz;
		}

		public LogPanel getPanel()
		{
			try
			{
				return clazz.getDeclaredConstructor().newInstance();
			}
			catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e)
			{
				return null;
			}
		}
	}

	public static List<LogPanel> getLogPanels()
	{
		return Arrays.asList(LogPanels.values()).stream().map(LogPanels::getPanel).collect(Collectors.toList());
	}
}
