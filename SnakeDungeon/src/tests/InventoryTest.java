package tests;

import game.controllers.MainController;
import game.controllers.RoomController;
import game.support.GameModel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.junit.Assert.*;
import static org.testfx.api.FxAssert.verifyThat;

public class InventoryTest extends ApplicationTest {

    private MainController controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new MainController();
        controller.start(primaryStage);
    }
    
    @Test
    public void testInventoryThere() throws Exception {
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
                GameModel gameModel = controller.getGameModel();
            }
            clickOn("#door1");
        }
    }

    @Test
    public void testDroppedItemThere() throws Exception {
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
            clickOn("#droppedItem");
            clickOn("#door1");
        }
    }
    
    @Test
    public void testItemAdded() throws Exception {
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
            clickOn("#droppedItem");
            clickOn("#door1");
        }
        assertEquals(10, controller.getGameModel().getInventoryString().size());
    }
    
    @Test
    public void testStartingWeapon() throws Exception{
        clickOn("#startButton");
        clickOn("#farmerName");
        write("testStaringWeapon");
        clickOn("#daggerButton");
        clickOn("#daggerButton");
        clickOn("#begin");
        GameModel gameModel = controller.getGameModel();
        ArrayList<String> testInventory = new ArrayList<>(Arrays.asList("Dagger"));
        assertEquals(testInventory, gameModel.getInventoryString());
    }

    @Test
    public void testHealthPotion() throws Exception{
        clickOn("#startButton");
        clickOn("#farmerName");
        write("testHealthPotion");
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
            clickOn("#droppedItem");
            Menu inventory = room.getPlayerMenu();
            inventory.setId("inventory");
            for (int j = 0; j < room.getPlayerMenu().getItems().size(); j++) {
                MenuItem item = inventory.getItems().get(j);
                if (item.getText().equalsIgnoreCase("health potion")) {
                    int oldHealth = controller.getGameModel().getHealth();
                    item.setId("item");
                    clickOn("#inventory").clickOn("#item");
                    if (oldHealth <= 90) {
                        assertEquals(oldHealth + 10, controller.getGameModel().getHealth());
                    } else {

                        assertEquals(100, controller.getGameModel().getHealth());
                    }
                }
            }
            clickOn("#door1");
        }
    }

}
