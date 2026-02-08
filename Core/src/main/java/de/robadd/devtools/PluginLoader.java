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

    public List<Plugin> getPlugins()
    {
        // new de.robadd.logfilter.ui.MainWindow();
        return new ArrayList<>();
    }
}
