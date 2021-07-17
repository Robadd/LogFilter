package de.robadd.logfilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Translator
{
	private Map<String, String> translations;
	private String locale;

	private static Translator instance;

	public static Translator getCurrent()
	{
		if (instance == null || instance.getLocale().equals(Config.getInstance().getLocale()))
		{
			instance = new Translator(Config.getInstance().getLocale());
		}
		return instance;
	}

	public String get(final String key)
	{
		String string = translations.get(key);
		return string == null ? key : string;
	}

	private Translator(final String locale)
	{
		translations = new HashMap<>();
		this.locale = locale;

		try (BufferedReader br = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(
			"translation/" + locale + ".txt"), StandardCharsets.UTF_8)))
		{
			String line;
			while ((line = br.readLine()) != null)
			{
				String[] split = line.split("=");
				translations.put(split[0], split[1]);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private String getLocale()
	{
		return locale;
	}
}
