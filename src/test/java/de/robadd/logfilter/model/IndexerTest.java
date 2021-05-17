package de.robadd.logfilter.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Arrays;
import java.util.Collections;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Test;

import de.robadd.logfilter.logtypes.serverlog.ServerLogConfiguration;

public class IndexerTest
{
	@Test
	public void serverLogTest() throws Exception
	{
		String resourceName = "oxidlog.log";

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(resourceName).getFile());
		LogHandler serverLogIndexer = new LogHandler(new ServerLogConfiguration());
		serverLogIndexer.setToReadingMode();

		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		final InputStream wellFormedXml = new SequenceInputStream(Collections.enumeration(Arrays.asList(new InputStream[]
		{ new ByteArrayInputStream("<dummy>".getBytes()), new FileInputStream(file), new ByteArrayInputStream("</dummy>"
				.getBytes()), })));
		parser.parse(wellFormedXml, serverLogIndexer);
		assertThat(serverLogIndexer.getIndex()).matches(a -> !a.getClassList().isEmpty(), "classlist").matches(a -> !a
				.getLogLevels().isEmpty(), "loglevelList").matches(a -> a.getMaxDate() != null, "maxDate").matches(a -> a
						.getMinDate() != null, "minDate");
		assertThat(serverLogIndexer.getIndex().getClassList()).doesNotHaveDuplicates();
		assertThat(serverLogIndexer.getIndex().getLogLevels()).doesNotHaveDuplicates();
	}
}
