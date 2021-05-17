package de.robadd.logfilter.ui.tabs;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
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
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import de.robadd.logfilter.logtypes.LogConfiguration;
import de.robadd.logfilter.model.Event;
import de.robadd.logfilter.model.EventFilter;
import de.robadd.logfilter.model.Index;
import de.robadd.logfilter.model.LogHandler;
import de.robadd.logfilter.ui.MainWindow;
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
	Collection<FilterPanel<?>> filterPanel = new ArrayList<>();
	private MainWindow parent;
	private LogHandler logHandler;
	private LogConfiguration config;

	/**
	 * @return the loadedFile
	 */
	protected File getInputFile()
	{
		return loadedFile;
	}

	public LogPanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		addFilteringPanel();
		addControlPanel();
		config = getLogConfiguration();
		logHandler = new LogHandler(config);
	}

	private void addFilteringPanel()
	{
		filter = new JPanel();
		filter.setLayout(new BoxLayout(filter, BoxLayout.Y_AXIS));
		add(filter);
	}

	private void addControlPanel()
	{
		controls = new JPanel();
		controls.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		add(controls);
		open = new JButton("Open");
		load = new JButton("Load");
		save = new JButton("Save");
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
		return new ActionListener()
		{
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File("C:\\programming\\eclipse2"));
				int result = fileChooser.showOpenDialog(open);
				if (result == JFileChooser.APPROVE_OPTION)
				{
					logHandler.setToReadingMode();
					logHandler.setInputFile(fileChooser.getSelectedFile());
				}
			}
		};
	}

	private ActionListener loadListener()
	{
		return new ActionListener()
		{
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				getLoadingThread().start();
			}
		};
	}

	private ActionListener saveFileListener()
	{
		return new ActionListener()
		{
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File("C:\\programming\\eclipse2"));
				int result = fileChooser.showSaveDialog(save);
				if (result == JFileChooser.APPROVE_OPTION)
				{
					final File file = fileChooser.getSelectedFile();
					try
					{
						logHandler.setToWritingMode(file, getEventFilters());
						parent.setStatus("Writing");
						SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
						final InputStream wellFormedXml = new SequenceInputStream(Collections.enumeration(Arrays.asList(
							new InputStream[]
						{ new ByteArrayInputStream("<dummy>".getBytes()), new FileInputStream(logHandler.getInputFile()),
								new ByteArrayInputStream("</dummy>".getBytes()), })));
						parser.parse(wellFormedXml, logHandler);
						parent.setStatus(MessageFormat.format("Writing finished {0} elements of {1} written", logHandler
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
		Thread t = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					logHandler.setToReadingMode();
					parent.resetProgressBar();
					parent.setStatus("Loading");
					SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
					final InputStream wellFormedXml = new SequenceInputStream(Collections.enumeration(Arrays.asList(
						new InputStream[]
					{ new ByteArrayInputStream("<dummy>".getBytes()), new FileInputStream(logHandler.getInputFile()),
							new ByteArrayInputStream("</dummy>".getBytes()), })));
					parser.parse(wellFormedXml, logHandler);
					updateFilterPanels(logHandler.getIndex());
					parent.setStatus("Loading finished. Read " + logHandler.getTotalEventCount() + " elements.");
				}
				catch (ParserConfigurationException | SAXException | IOException e1)
				{
					e1.printStackTrace();
				}
			}
		});
		return t;
	}

	protected abstract LogConfiguration getLogConfiguration();

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
		this.parent = parent;
	}

	public abstract int priority();

	/**
	 * Overwrite this method to return true if its fully implemented abd should be
	 * shown
	 *
	 * @return if implemented
	 */
	public boolean isImplemented()
	{
		return false;
	}
}