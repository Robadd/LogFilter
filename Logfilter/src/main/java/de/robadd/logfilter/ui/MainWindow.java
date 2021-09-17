package de.robadd.logfilter.ui;

import static com.jgoodies.forms.layout.FormSpecs.LABEL_COMPONENT_GAP_COLSPEC;
import static com.jgoodies.forms.layout.FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC;
import static com.jgoodies.forms.layout.FormSpecs.MIN_ROWSPEC;
import static javax.swing.SwingConstants.BOTTOM;
import static javax.swing.border.BevelBorder.LOWERED;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import de.robadd.Plugin;
import de.robadd.logfilter.InterfaceLoader;
import de.robadd.logfilter.Translator;
import de.robadd.logfilter.ui.tabs.LogPanel;

public class MainWindow extends JFrame implements Plugin
{
    private static final long serialVersionUID = 1L;
    private JLabel status;
    private JProgressBar progressBar;
    private JTabbedPane filterPanels;

    /**
     * Create the application.
     *
     * @param show if ui should be shown
     */
    public MainWindow()
    {
        this(true);
    }

    /**
     * Create the application.
     *
     * @param show if ui should be shown
     */
    public MainWindow(final boolean show)
    {
        initialize();
        setVisible(show);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize()
    {
        setBounds(100, 100, 800, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new FormLayout(new ColumnSpec[]
        {ColumnSpec.decode("784px:grow"),}, new RowSpec[]
        {RowSpec.decode("fill:pref:grow"), MIN_ROWSPEC,}));
        filterPanels = new JTabbedPane(BOTTOM);
        filterPanels.setName("TabbedPaneFilterPanels");
        getContentPane().add(filterPanels, "1, 1, fill, fill");
        addPanels();

        JPanel panel = new JPanel();
        panel.setBorder(new BevelBorder(LOWERED, null, null, null, null));
        getContentPane().add(panel, "1, 2, fill, top");
        panel.setLayout(new FormLayout(new ColumnSpec[]
        {LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("left:default"), ColumnSpec.decode("default:grow"), ColumnSpec
                .decode("right:default"), LABEL_COMPONENT_GAP_COLSPEC,}, new RowSpec[]
        {LABEL_COMPONENT_GAP_ROWSPEC, RowSpec.decode("default:grow"), LABEL_COMPONENT_GAP_ROWSPEC,}));

        status = new JLabel(Translator.getCurrent().get("Status"));
        getStatus().setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(getStatus(), "2, 2, left, top");

        progressBar = new JProgressBar();
        panel.add(getProgressBar(), "4, 2, left, top");
    }

    private void addPanels()
    {
        InterfaceLoader.getLogPanels().stream().forEach(tab ->
        {
            tab.setParent(this);
            filterPanels.addTab(tab.getTitle(), tab.getIcon(), tab, null);
        });
        Component[] components = filterPanels.getComponents();
        for (int i = 0; i < components.length; i++)
        {
            Component component = components[i];
            if (component instanceof LogPanel)
            {
                filterPanels.setEnabledAt(i, ((LogPanel) component).isImplemented());
            }
        }
    }

    @SuppressWarnings("unused")
    private DropTarget getDopTarget()
    {
        return new DropTarget()
        {
            private static final long serialVersionUID = -5655403058080630157L;

            @Override
            public synchronized void drop(final DropTargetDropEvent evt)
            {
                // For future implementation
            }
        };
    }

    public void setStatus(final String text)
    {
        EventQueue.invokeLater(() -> getStatus().setText(text));
    }

    public JProgressBar getProgressBar()
    {
        return progressBar;
    }

    /**
     * @return the frame
     */
    JTabbedPane getFilterPanelFrame()
    {
        return filterPanels;
    }

    JFrame getFrame()
    {
        return this;
    }

    JLabel getStatus()
    {
        return status;
    }

    /**
     * {@inheritDoc}
     *
     * @see de.robadd.Plugin#getMainWindowFrame()
     */
    @Override
    public JFrame getMainWindowFrame()
    {
        return this;
    }
}
