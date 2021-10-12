package de.robadd.devtools;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.robadd.devtools.interfaces.Plugin;

/**
 * <b>Title:</b> Main <br>
 * <b>Copyright:</b> Copyright (c) 2021 <br>
 * <b>Company:</b> Speed4Trade GmbH <br>
 * <b>Description:</b> <br>
 *
 * @author rok
 * @version $Id: Main.java 1 17.09.2021 rok $
 */
class Main
{

	public static void main(final String[] args) throws Exception
	{
		List<Class<? extends Plugin>> plugins = PluginLoader.getInstance().getPlugins();
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}
		EventQueue.invokeLater(() ->
		{
			try
			{
				plugins.forEach(a ->
				{
					try
					{
						a.getDeclaredConstructor(Boolean.class).newInstance(true);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				});
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		});
	}

}
