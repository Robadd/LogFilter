package de.robadd.logfilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
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
		String locale = Config.getInstance().getLocale();
		if (instance == null || instance.getLocale().equals(locale))
		{
			instance = new Translator(locale);
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
		InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream("translation/" + locale + ".txt");
		if (systemResourceAsStream == null)
		{
			return;
		}
		try (BufferedReader br = new BufferedReader(new InputStreamReader(systemResourceAsStream,
				StandardCharsets.UTF_8)))
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
