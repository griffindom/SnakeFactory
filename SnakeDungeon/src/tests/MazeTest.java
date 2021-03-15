package tests;

import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import sample.Main;

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

    @Test
    public void testDoor1Through() {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("Not empty");
        clickOn("#longSwordButton");
        clickOn("#begin");
        clickOn("#door1");
        clickOn("#door1");
        clickOn("#door1");
        clickOn("#door1");
        clickOn("#door1");
        clickOn("#door1");
        clickOn("#door1");
        clickOn("#door1");
        clickOn("#door1");
    }

    @Test
    public void testDoor1GoBackFunc() {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("Not empty");
        clickOn("#longSwordButton");
        clickOn("#begin");
        clickOn("#door1");
        clickOn("#goBack");
        clickOn("#door1");

    }

    @Test
    public void testDoor3Through() {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("Not empty");
        clickOn("#longSwordButton");
        clickOn("#begin");
        for (int i = 0; i < 10; i++) {
            if (false) {
                clickOn("#door3");
            } else {
                clickOn("#door1");
            }
        }
    }
    @Test
    public void testDoor3GoBackFunc() {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("Not empty");
        clickOn("#longSwordButton");
        clickOn("#begin");
        if (lookup("#door3").query() != null) {
            clickOn("#door3");
        } else {
            clickOn("#door1");
        }
        clickOn("#goBack");
        if (lookup("#door3").query() != null) {
            clickOn("#door3");
        } else {
            clickOn("#door1");
        }
    }

    @Test
    public void testEndScreenMultipleDoors() {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("Gerald");
        clickOn("#daggerButton");
        clickOn("#begin");
        for (int i = 0; i < 10; i++) {
            if (false) {
                clickOn("#door2");
            } else {
                clickOn("#door1");
            }
        }
        verifyThat("You escaped the Snake Dungeon!", NodeMatchers.isNotNull());
    }
    @Test
    public void testRoom1toEndScreen() {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("Aslan");
        clickOn("#daggerButton");
        clickOn("#begin");
        clickOn("#door4");
        clickOn("#door1");
        clickOn("#door1");
        clickOn("#door1");
        clickOn("#door1");
        clickOn("#door1");
        clickOn("#door1");
        clickOn("#door1");
        clickOn("#door1");
        clickOn("#door1");
        verifyThat("You escaped the Snake Dungeon!", NodeMatchers.isNotNull());
    }
     @Test
    public void testGoBackTwiceFunc() {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("Not empty");
        clickOn("#longSwordButton");
        clickOn("#begin");
        clickOn("#door1");
        clickOn("#door1");
        clickOn("#door1");
        clickOn("#goBack");
        clickOn("#goBack");
    }
    @Test
    public void testLastRoomToFirst() {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("Not empty");
        clickOn("#longSwordButton");
        clickOn("#begin");
        for (int i = 0; i < 9; i++) {
            clickOn("#door1");
        }
        for (int i = 0; i < 9; i++) {
            clickOn("#goBack");
    }

    @Test
    public void testGoBackTwiceFunc() {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("Not empty");
        clickOn("#longSwordButton");
        clickOn("#begin");
        clickOn("#door1");
        clickOn("#door1");
        clickOn("#door1");
        clickOn("#goBack");
        clickOn("#goBack");
    }

    @Test
    public void testLastRoomToFirst() {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("Not empty");
        clickOn("#longSwordButton");
        clickOn("#begin");
        for (int i = 0; i < 9; i++) {
            clickOn("#door1");
        }
        for (int i = 0; i < 9; i++) {
            clickOn("#goBack");
        }

    }
}
