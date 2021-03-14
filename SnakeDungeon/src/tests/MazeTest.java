package tests;

import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import sample.Main;
import sample.RoomController;

import static org.testfx.api.FxAssert.verifyThat;

public class MazeTest extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main controller = new Main();
        controller.start(primaryStage);
    }

    @Test
    public void testFirstRoom() {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("Not empty");
        clickOn("#longSwordButton");
        clickOn("#begin");
        verifyThat("#door4", NodeMatchers.isNotNull());
    }

    @Test
    public void testEndScreen() {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("Not empty");
        clickOn("#longSwordButton");
        clickOn("#begin");
        for (int i = 0; i < 10; i++) {
            clickOn("#door1");
        }
        verifyThat("You escaped the Snake Dungeon!", NodeMatchers.isNotNull());
    }
}
