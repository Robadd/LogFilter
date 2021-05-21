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
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

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

import de.robadd.logfilter.InterfaceLoader;
import de.robadd.logfilter.ui.tabs.LogPanel;

public class MainWindow
{
    private JFrame frame;
    private File file;
    private Collection<LogPanel> tabs = new ArrayList<>();
    private JLabel status;
    private JProgressBar progressBar;

    /**
     * Create the application.
     */
    public MainWindow()
    {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    @SuppressWarnings("resource")
    private void initialize()
    {
        frame = new JFrame();
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FormLayout(new ColumnSpec[]
        {ColumnSpec.decode("784px:grow"),}, new RowSpec[]
        {RowSpec.decode("fill:pref:grow"), MIN_ROWSPEC,}));
        JTabbedPane tabbedPane = new JTabbedPane(BOTTOM);
        frame.getContentPane().add(tabbedPane, "1, 1, fill, fill");
        addPanels(tabbedPane);

        JPanel panel = new JPanel();
        panel.setBorder(new BevelBorder(LOWERED, null, null, null, null));
        frame.getContentPane().add(panel, "1, 2, fill, top");
        panel.setLayout(new FormLayout(new ColumnSpec[]
        {LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("left:default"), ColumnSpec.decode("default:grow"),
                ColumnSpec.decode("right:default"), LABEL_COMPONENT_GAP_COLSPEC,}, new RowSpec[]
        {LABEL_COMPONENT_GAP_ROWSPEC, RowSpec.decode("default:grow"),
                LABEL_COMPONENT_GAP_ROWSPEC,}));

        status = new JLabel("Status");
        status.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(status, "2, 2, left, top");

        progressBar = new JProgressBar();
        panel.add(getProgressBar(), "4, 2, left, top");
    }

    private void addPanels(final JTabbedPane tabbedPane)
    {
        tabs.addAll(InterfaceLoader.getLogPanels());
        tabs.stream().forEach(tab ->
        {
            tab.setParent(this);
            tabbedPane.addTab(tab.getTitle(), tab.getIcon(), tab, null);
        });
        Component[] components = tabbedPane.getComponents();
        for (int i = 0; i < components.length; i++)
        {
            Component component = components[i];
            if (component instanceof LogPanel)
            {
                tabbedPane.setEnabledAt(i, ((LogPanel) component).isImplemented());
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
                // try
                // {
                // evt.acceptDrop(DnDConstants.ACTION_COPY);
                // List<File> droppedFiles = (List<File>) evt.getTransferable().getTransferData(
                // DataFlavor.javaFileListFlavor);
                // for (File file : droppedFiles)
                // {
                // LogIndexHandler.indexServerLog(file);
                // props.fillFilterBoxes();
                // }
                // }
                // catch (Exception ex)
                // {
                // ex.printStackTrace();
                // }
            }
        };
    }

    public void setStatus(final String text)
    {
        EventQueue.invokeLater(() -> status.setText(text));

    }

    public void setFrameVisible()
    {
        this.frame.setVisible(true);
    }

    /**
     * @return the file
     */
    public File getFile()
    {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(final File file)
    {
        this.file = file;
    }

    public void setProgressBarMaximum(final Integer max)
    {
        getProgressBar().setMaximum(max);
    }

    public void setProgress(final Integer val)
    {
        getProgressBar().setValue(val);
    }

    public void resetProgressBar()
    {
        getProgressBar().setMaximum(1);
        getProgressBar().setValue(0);
    }

    public JProgressBar getProgressBar()
    {
        return progressBar;
    }
}
