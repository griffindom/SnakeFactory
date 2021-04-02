package tests;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import game.support.GameModel;
import game.controllers.MainController;
import game.controllers.RoomController;


import static org.junit.Assert.*;
import static org.testfx.api.FxAssert.verifyThat;

public class SnakeTest extends ApplicationTest {

    private MainController controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new MainController();
        controller.start(primaryStage);
    }

    @Test
    public void testSnakesSpawn() throws Exception {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("Not empty");
        clickOn("#longSwordButton");
        clickOn("#begin");
        clickOn("#door1");
        for (int i = 0; i < 9; i++) {
            RoomController room = controller.getGameModel().getMaze().getTail().getData();
            Scene scene = room.getScene();
            Button snake = (Button) scene.lookup("#snake");
            while (snake != null) {
                clickOn("#snake");
                snake = (Button) scene.lookup("#snake");
            }
            clickOn("#door1");
        }
        verifyThat("You escaped the Snake Dungeon!", NodeMatchers.isNotNull());
    }

    @Test
    public void testDeath() throws Exception {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("Not empty");
        drag("#difficultySlider");
        clickOn("#daggerButton");
        clickOn("#daggerButton");
        clickOn("#begin");
        clickOn("#door1");
        GameModel gameModel = controller.getGameModel();
        while (gameModel.getHealth() > 0) {
            RoomController room = controller.getGameModel().getMaze().getTail().getData();
            Scene scene = room.getScene();
            Button snake = (Button) scene.lookup("#snake");
            while (snake != null && gameModel.getHealth() > 0) {
                clickOn("#snake");
                snake = (Button) scene.lookup("#snake");
            }
            if (gameModel.getHealth() > 0) {
                clickOn("#door1");
            }
        }
        verifyThat("Choose difficulty", NodeMatchers.isNotNull());
    }
    
    @Test
    public void testRetreatAndReenter() throws Exception {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("Retreat Test 1");
        clickOn("#daggerButton");
        clickOn("#daggerButton");
        clickOn("#begin");
        clickOn("#door1");
        GameModel gameModel = controller.getGameModel();
        RoomController room = controller.getGameModel().getMaze().getTail().getData();
        Scene scene = room.getScene();
        Button snake = (Button) scene.lookup("#snake");
        clickOn("#snake");
        int firstHealth = room.getSnakeHealth();
        clickOn("#goBack");
        clickOn("#door1");
        int secondHealth = room.getSnakeHealth();
        assertEquals(firstHealth, secondHealth);
    }
    
    @Test
    public void testRetreatThenNewRoom() throws Exception {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("Retreat Test 1");
        clickOn("#daggerButton");
        clickOn("#daggerButton");
        clickOn("#begin");
        clickOn("#door1");
        GameModel gameModel = controller.getGameModel();
        RoomController room = controller.getGameModel().getMaze().getTail().getData();
        Scene scene = room.getScene();
        Button snake = (Button) scene.lookup("#snake");
        clickOn("#snake");
        int firstHealth = room.getSnakeHealth();
        clickOn("#goBack");
        clickOn("#door2");
        gameModel = controller.getGameModel();
        room = controller.getGameModel().getMaze().getTail().getData();
        int secondHealth = room.getSnakeHealth();
        assertNotEquals(firstHealth, secondHealth);
    }

    @Test
    public void testPlayerDamage() throws Exception {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("Not empty");
        clickOn("#longSwordButton");
        clickOn("#begin");
        clickOn("#door1");
        GameModel gameModel = controller.getGameModel();
        RoomController room = controller.getGameModel().getMaze().getTail().getData();
        Scene scene = room.getScene();
        int fullHP = gameModel.getHealth();
        Button snake = (Button) scene.lookup("#snake");
        clickOn("#snake");
        int lowerHP = gameModel.getHealth();
        assertNotEquals(fullHP, lowerHP);
    }

    @Test
    public void testHealthDoesntResetWhenReenteringRoom() throws Exception {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("Not empty");
        clickOn("#longSwordButton");
        clickOn("#begin");
        clickOn("#door1");
        GameModel gameModel = controller.getGameModel();
        RoomController room = controller.getGameModel().getMaze().getTail().getData();
        Scene scene = room.getScene();
        Button snake = (Button) scene.lookup("#snake");
        clickOn("#snake");
        int firstHP = gameModel.getHealth();
        clickOn("#goBack");
        clickOn("#door1");
        int secondHP = gameModel.getHealth();
        assertEquals(firstHP, secondHP);
    }

    @Test
    public void testMaceDamage() throws Exception {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("Mace Damage");
        clickOn("#maceButton");
        clickOn("#begin");
        clickOn("#door1");
        GameModel gameModel = controller.getGameModel();
        RoomController room = controller.getGameModel().getMaze().getTail().getData();
        Scene scene = room.getScene();
        int fullHP = gameModel.getHealth();
        Button snake = (Button) scene.lookup("#snake");
        clickOn("#snake");
        int damage = gameModel.getAttackValue();
        assertTrue("Error, damage is too low", damage >= 7);
        assertTrue("Error, damage is too high", damage <= 16);
    }

    @Test
    public void testLongSwordDamage() throws Exception {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("LSwordDamage");
        clickOn("#longSwordButton");
        clickOn("#begin");
        clickOn("#door1");
        GameModel gameModel = controller.getGameModel();
        RoomController room = controller.getGameModel().getMaze().getTail().getData();
        Scene scene = room.getScene();
        int fullHP = gameModel.getHealth();
        Button snake = (Button) scene.lookup("#snake");
        clickOn("#snake");
        int damage = gameModel.getAttackValue();
        assertTrue("Error, damage is too low", damage >= 5);
        assertTrue("Error, damage is too high", damage <= 16);
    }

    @Test
    public void testDaggerDamage() throws Exception {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("Dagger Damage");
        clickOn("#daggerButton");
        clickOn("#begin");
        clickOn("#door1");
        GameModel gameModel = controller.getGameModel();
        RoomController room = controller.getGameModel().getMaze().getTail().getData();
        Scene scene = room.getScene();
        int fullHP = gameModel.getHealth();
        Button snake = (Button) scene.lookup("#snake");
        clickOn("#snake");
        int damage = gameModel.getAttackValue();
        assertTrue("Error, damage is too low", damage >= 2);
        assertTrue("Error, damage is too high", damage <= 6);
    }
    
    @Test
    public void testDamageNumbers() throws Exception {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("Not empty");
        clickOn("#longSwordButton");
        clickOn("#begin");
        clickOn("#door1");
        GameModel gameModel = controller.getGameModel();
        RoomController room = controller.getGameModel().getMaze().getTail().getData();
        Scene scene = room.getScene();
        int fullHP = gameModel.getHealth();
        Button snake = (Button) scene.lookup("#snake");
        clickOn("#snake");
        int damage = gameModel.getAttackValue();
        assertTrue("Error, random is too low", damage >= 5);
        assertTrue("Error, random is too high", damage <= 16);
    }
}
