package de.robadd.devtools;

import java.util.ArrayList;
import java.util.List;

import de.robadd.devtools.interfaces.Plugin;

class PluginLoader
{
	private static final PluginLoader INSTANCE = new PluginLoader();

	public static PluginLoader getInstance()
	{
		return INSTANCE;
	}

	private PluginLoader()
	{
	}

	public List<Class<? extends Plugin>> getPlugins()
	{
		return new ArrayList<>();
	}
}
