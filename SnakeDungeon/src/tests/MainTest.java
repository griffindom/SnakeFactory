package tests;

import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import sample.Main;

import static org.testfx.api.FxAssert.verifyThat;

public class MainTest extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main controller = new Main();
        controller.start(primaryStage);
    }

    @Test
    public void testWelcomeScreen() {
        verifyThat("Dungeon Crawler", NodeMatchers.isNotNull());
    }

    @Test
    public void testStart() {
        clickOn("#startButton");
        verifyThat("Choose difficulty", NodeMatchers.isNotNull());
    }
}
