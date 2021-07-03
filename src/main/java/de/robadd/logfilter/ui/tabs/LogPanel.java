package de.robadd.logfilter.ui.tabs;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import de.robadd.logfilter.Config;
import de.robadd.logfilter.Translator;
import de.robadd.logfilter.logtypes.LogConfiguration;
import de.robadd.logfilter.model.Event;
import de.robadd.logfilter.model.EventFilter;
import de.robadd.logfilter.model.Index;
import de.robadd.logfilter.model.LogHandler;
import de.robadd.logfilter.ui.MainWindow;
import de.robadd.logfilter.ui.MonitoredInputStream;
import de.robadd.logfilter.ui.filter.FilterPanel;

public abstract class LogPanel extends JPanel
{
	private static final long serialVersionUID = -4943890136295385228L;
	private JPanel controls;
	private JPanel filter;
	private JButton load;
	private File loadedFile;
	private JButton open;
	private JButton save;
	protected Integer totalCount = 0;
	private transient Collection<FilterPanel<?>> filterPanel = new ArrayList<>();
	private transient MainWindow parentWindow;
	private transient LogHandler logHandler;
	private transient LogConfiguration config;

	/**
	 * @return the loadedFile
	 */
	protected File getInputFile()
	{
		return loadedFile;
	}

	protected LogPanel()
	{
		addFilteringPanel();
		addControlPanel();
		config = getLogConfiguration();
		logHandler = new LogHandler(config);
	}

	private void addFilteringPanel()
	{
		setLayout(new FormLayout(new ColumnSpec[]
		{ ColumnSpec.decode("450px"), }, new RowSpec[]
		{ RowSpec.decode("default:grow"), FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.MIN_ROWSPEC, }));
		filter = new JPanel();
		filter.setLayout(new BoxLayout(filter, BoxLayout.Y_AXIS));
		add(filter, "1, 1, center, center");
	}

	private void addControlPanel()
	{
		controls = new JPanel();
		controls.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		add(controls, "1, 3, center, bottom");
		open = new JButton(Translator.getCurrent().get("Open"));
		open.setName("BtnOpen");
		load = new JButton(Translator.getCurrent().get("Load"));
		load.setName("BtnLoad");
		save = new JButton(Translator.getCurrent().get("Save"));
		save.setName("BtnSave");
		controls.add(open);
		controls.add(load);
		controls.add(save);
	}

	public abstract String getTitle();

	public abstract Icon getIcon();

	/**
	 * @return the filterPanel
	 */
	protected Collection<FilterPanel<?>> getFilterPanel()
	{
		return filterPanel;
	}

	protected void init()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		getFilterPanels().stream().forEach(filterPanel::add);
		filterPanel.stream().forEach(filter::add);
		open.addActionListener(openFileListener());
		load.addActionListener(loadListener());
		save.addActionListener(saveFileListener());
	}

	protected abstract Collection<FilterPanel<?>> getFilterPanels();

	public JPanel getControls()
	{
		return controls;
	}

	public JPanel getFilter()
	{
		return filter;
	}

	private ActionListener openFileListener()
	{
		return e ->
		{
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(Config.getInstance().getInDirectory()));
			int result = fileChooser.showOpenDialog(open);
			if (result == JFileChooser.APPROVE_OPTION)
			{
				logHandler.setToReadingMode();
				logHandler.setInputFile(fileChooser.getSelectedFile());
			}
		};
	}

	private ActionListener loadListener()
	{
		return e -> getLoadingThread().start();
	}

	private ActionListener saveFileListener()
	{
		return e -> getSavingThread().start();
	}

	private Thread getSavingThread()
	{
		return new Thread()
		{
			@Override
			public void run()
			{
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(Config.getInstance().getOutDirectory()));
				int result = fileChooser.showSaveDialog(save);
				if (result == JFileChooser.APPROVE_OPTION)
				{
					final File file = fileChooser.getSelectedFile();
					logHandler.setToWritingMode(file, getEventFilters());
					parentWindow.setStatus(Translator.getCurrent().get("status.writing"));
					try (final MonitoredInputStream monitoredInputStream = new MonitoredInputStream(logHandler
							.getInputFile(), parentWindow.getProgressBar());)
					{
						SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
						parser.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
						parser.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

						final InputStream wellFormedXml = new SequenceInputStream(Collections.enumeration(Arrays.asList(
							new ByteArrayInputStream("<dummy>".getBytes()), monitoredInputStream, new ByteArrayInputStream(
									"</dummy>".getBytes()))));
						parser.parse(wellFormedXml, logHandler);
						parentWindow.setStatus(MessageFormat.format(Translator.getCurrent().get("status.written"), logHandler
								.getHandledEventCount(), logHandler.getTotalEventCount()));
					}
					catch (IOException | ParserConfigurationException | SAXException e1)
					{
						e1.printStackTrace();
					}
				}
			}
		};
	}

	private Thread getLoadingThread()
	{
		return new Thread(() ->
		{
			logHandler.setToReadingMode();
			parentWindow.setStatus(Translator.getCurrent().get("status.loading"));
			try (final MonitoredInputStream monitoredInputStream = new MonitoredInputStream(logHandler.getInputFile(),
					parentWindow.getProgressBar());)
			{
				SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
				parser.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
				parser.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

				final InputStream wellFormedXml = new SequenceInputStream(Collections.enumeration(Arrays.asList(
					new ByteArrayInputStream("<dummy>".getBytes()), monitoredInputStream, new ByteArrayInputStream("</dummy>"
							.getBytes()))));
				parser.parse(wellFormedXml, logHandler);
				updateFilterPanels(logHandler.getIndex());
				parentWindow.setStatus(MessageFormat.format(Translator.getCurrent().get("status.loaded"), logHandler
						.getTotalEventCount()));
			}
			catch (ParserConfigurationException | SAXException | IOException e1)
			{
				e1.printStackTrace();
			}
		});
	}

	public abstract LogConfiguration getLogConfiguration();

	protected void updateFilterPanels(final Index index)
	{
		filterPanel.stream().forEach(a -> a.setValuesFromIndex(index));
	}

	public final Collection<EventFilter<Event, ?>> getEventFilters()
	{
		return filterPanel.stream().map(FilterPanel::getEventFilter).collect(Collectors.toList());
	}

	public void setParent(final MainWindow parent)
	{
		this.parentWindow = parent;
	}

	/**
	 * Overwrite this method to return true if its fully implemented and should be
	 * shown
	 *
	 * @return if implemented
	 */
	public boolean isImplemented()
	{
		return false;
	}
}
