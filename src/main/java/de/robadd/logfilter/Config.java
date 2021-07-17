package de.robadd.logfilter;

public final class Config
{
	private static final Config INSTANCE = new Config();
	private String inDirectory;
	private String outDirectory;
	private String locale;

	/**
	 * private Constructor
	 */
	private Config()
	{
		super();
		inDirectory = "C:\\programming\\eclipse2";
		outDirectory = "C:\\programming\\eclipse2";
		locale = "de";
	}

	/**
	 * @return the instance
	 */
	public static Config getInstance()
	{
		return INSTANCE;
	}

	/**
	 * @return the inDirectory
	 */
	public String getInDirectory()
	{
		return inDirectory;
	}

	/**
	 * @return the outDirectory
	 */
	public String getOutDirectory()
	{
		return outDirectory;
	}

	/**
	 * @return the locale
	 */
	public String getLocale()
	{
		return locale;
	}
}
