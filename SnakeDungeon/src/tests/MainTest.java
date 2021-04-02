package tests;

import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.LabeledMatchers;
import game.controllers.MainController;

import static org.testfx.api.FxAssert.verifyThat;

public class MainTest extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainController controller = new MainController();
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

    @Test
    public void testMaceButton() {
        clickOn("#startButton");
        clickOn("#maceButton");
        FxAssert.verifyThat("#maceButton", LabeledMatchers.hasText("Mace"));
    }

    @Test
    public void testDaggerButton() {
        clickOn("#startButton");
        clickOn("#daggerButton");
        FxAssert.verifyThat("#daggerButton", LabeledMatchers.hasText("Dagger"));
    }

    @Test
    public void testNameValidity() {
        clickOn("#startButton");
        verifyThat("Begin", NodeMatchers.isNotNull());
    }

    @Test
    public void testStartUp() {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("Not empty");
        clickOn("#difficultySlider");
        clickOn("#longSwordButton");
        clickOn("#begin");
        verifyThat("450 Gold Coins", NodeMatchers.isNotNull());
    }

    @Test
    public void testStartUp2() {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("Not empty");
        clickOn("#longSwordButton");
        clickOn("#begin");
        verifyThat("500 Gold Coins", NodeMatchers.isNotNull());
    }

    @Test
    public void testLongSwordButton() {
        clickOn("#startButton");
        clickOn("#longSwordButton");
        FxAssert.verifyThat("#longSwordButton", LabeledMatchers.hasText("Long Sword"));
    }

    @Test
    public void emptyNameNotAllowed() {
        clickOn("#startButton");
        clickOn("#longSwordButton");
        clickOn("#begin");
        verifyThat("Choose difficulty", NodeMatchers.isNotNull());
    }

    @Test
    public void testBegin() {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("Not empty");
        clickOn("#begin");
        verifyThat("Choose difficulty", NodeMatchers.isNotNull());
    }
}
