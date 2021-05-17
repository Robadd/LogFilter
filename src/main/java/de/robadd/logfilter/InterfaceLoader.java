package de.robadd.logfilter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

import de.robadd.logfilter.ui.tabs.LogPanel;

public class InterfaceLoader
{

	public static List<LogPanel> getLogPanels()
	{
		List<LogPanel> retVal = new ArrayList<>();

		final ClassLoader loader = ClassLoader.getSystemClassLoader();
		try
		{
			final ImmutableSet<ClassInfo> allClasses = ClassPath.from(loader).getAllClasses();
			final List<ClassInfo> packageFilteredClasses = allClasses.stream().filter(a -> a.getName().startsWith(
				"de.robadd.logfilter.ui.tabs")).collect(Collectors.toList());
			for (ClassInfo classInfo : packageFilteredClasses)
			{
				if (Arrays.asList(classInfo.load().getSuperclass()).stream().anyMatch(a -> a != null && a.getName().equals(
					LogPanel.class.getName())))
				{
					Class<?> clazz = classInfo.load();
					LogPanel newInstance = (LogPanel) clazz.getDeclaredConstructor().newInstance();
					retVal.add(newInstance);
				}
			}

		}
		catch (IOException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		return retVal;
	}
}
