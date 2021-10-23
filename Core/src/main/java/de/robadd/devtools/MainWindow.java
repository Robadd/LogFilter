package de.robadd.devtools;

import static java.awt.BorderLayout.CENTER;
import static javax.swing.SwingConstants.TOP;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import de.robadd.devtools.interfaces.Plugin;

public class MainWindow extends JFrame
{
	private static final long serialVersionUID = 4105015674387327346L;
	private transient List<Plugin> plugins = new ArrayList<>();
	private JTabbedPane tabbedPane;

	/**
	 * Create the application.
	 */
	public MainWindow()
	{
		initialize();
		addPlugins();
		setVisible(true);
	}

	/**
	 * add a plugin
	 *
	 * @param plugin the plugin
	 */
	public void addPlugin(final Plugin plugin)
	{
		plugins.add(plugin);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		tabbedPane = new JTabbedPane(TOP);
		getContentPane().add(tabbedPane, CENTER);
	}

	private void addPlugins()
	{
		for (Plugin plugin : plugins)
		{
			try
			{
				JFrame instance = plugin.getMainWindowFrameClass().newInstance();
				tabbedPane.add(instance);
			}
			catch (InstantiationException | IllegalAccessException e)
			{
				e.printStackTrace();
			}
		}
	}
}
