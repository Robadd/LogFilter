package de.robadd.logfilter.ui;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Function;

import javax.swing.JTabbedPane;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JFileChooserFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.assertj.swing.timing.Timeout;
import org.junit.Ignore;
import org.junit.Test;

import de.robadd.logfilter.InterfaceLoader;

@Ignore("Needs to be run manually, because of the file chooser dialog")
public class MainWindowTest extends AssertJSwingJUnitTestCase
{

    private FrameFixture window;

    @Override
    protected void onSetUp() throws Exception
    {
        MainWindow frame = GuiActionRunner.execute(() -> new MainWindow(true));
        window = new FrameFixture(robot(), frame);

    }

    @Test
    public void openDialogTest()
    {
        JFileChooserFixture fileChooser = null;
        assertThat(fileChooser).isNull();
        window.button("BtnOpen").click();
        fileChooser = window.fileChooser(Timeout.timeout(1000));
        assertThat(fileChooser).isNotNull();
    }

    @Test
    public void mainTest()
    {
        JTabbedPane frame = window.targetCastedTo(MainWindow.class).getFilterPanelFrame();
        assertThat(frame).extracting((Function<? super JTabbedPane, Integer>) JTabbedPane::getTabCount).isEqualTo(
            InterfaceLoader.getLogPanels().size());
    }

    @Test
    public void statusTest() throws InterruptedException
    {
        MainWindow mainWindow = window.targetCastedTo(MainWindow.class);
        assertThat(mainWindow.getStatus().getText()).isEqualTo("Status");
        mainWindow.setStatus("Foobar");
        Thread.sleep(100);
        assertThat(mainWindow.getStatus().getText()).isEqualTo("Foobar");
    }

}
