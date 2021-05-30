package de.robadd.logfilter;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.robadd.logfilter.ui.MainWindow;

public class Main
{

    public static void main(final String[] args)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e1)
        {
            e1.printStackTrace();
        }
        EventQueue.invokeLater(() ->
        {
            try
            {
                MainWindow window = new MainWindow();
                window.setFrameVisible();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });

    }

}
