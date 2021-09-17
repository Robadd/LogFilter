package de.robadd.devtools;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * <b>Title:</b> Main <br>
 * <b>Copyright:</b> Copyright (c) 2021 <br>
 * <b>Company:</b> Speed4Trade GmbH <br>
 * <b>Description:</b> <br>
 *
 * @author rok
 * @version $Id: Main.java 1 17.09.2021 rok $
 */
public class Main
{

    /**
     * main
     *
     * @param args
     * @throws ClassNotFoundException
     */
    public static void main(final String[] args) throws ClassNotFoundException
    {
        Class<? extends Plugin> logfilter = (Class<? extends Plugin>) ClassLoader.getSystemClassLoader().loadClass("MainWindow");
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e)
        {
            e.printStackTrace();
        }
        EventQueue.invokeLater(() ->
        {
            try
            {
                logfilter.getDeclaredConstructor().newInstance();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });
    }

}
