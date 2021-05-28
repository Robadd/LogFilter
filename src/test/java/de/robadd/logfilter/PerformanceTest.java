package de.robadd.logfilter;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Arrays;
import java.util.Collections;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import de.robadd.logfilter.logtypes.serverlog.ServerLogConfiguration;
import de.robadd.logfilter.model.LogHandler;

public class PerformanceTest
{
	@Test
	public void performance() throws Exception
	{
		long start = System.currentTimeMillis();
		p(300000);
		Assertions.assertThat(System.currentTimeMillis() - start).isLessThan(3100);
	}

	private void p(final int i) throws Exception
	{
		String resourceName = "oxidlog.log";

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(resourceName).getFile());
		LogHandler serverLogIndexer = new LogHandler(new ServerLogConfiguration());
		serverLogIndexer.setToReadingMode();

		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		final InputStream wellFormedXml = new SequenceInputStream(Collections.enumeration(Arrays.asList(new InputStream[]
		{ new ByteArrayInputStream("<dummy>".getBytes()), new MultiplierFileStream(file, i), new ByteArrayInputStream(
				"</dummy>".getBytes()), })));
		parser.parse(wellFormedXml, serverLogIndexer);
	}
}
