package tests;

import game.controllers.MainController;
import game.controllers.RoomController;
import game.support.GameModel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

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
    public void testStartingWeapon() throws Exception {
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
    public void testHealthPotion() throws Exception {
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
    
    @Test
    public void testItemStays() throws Exception {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("LostItem");
        clickOn("#longSwordButton");
        clickOn("#begin");
        for (int i = 0; i < 2; i++) {
            clickOn("#door1");
            GameModel gameModel = controller.getGameModel();
            RoomController room = controller.getGameModel().getMaze().getTail().getData();
            Scene scene = room.getScene();
            Button snake = (Button) scene.lookup("#snake");
            while (snake != null && gameModel.getHealth() > 0) {
                clickOn("#snake");
                snake = (Button) scene.lookup("#snake");
            }
        }
        clickOn("#door1");
        clickOn("#goBack");
        System.out.println("goin back");
        clickOn("#droppedItem");
    }
    
    @Test
    public void useMulitpleItems() throws Exception {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("Multiple");
        clickOn("#longSwordButton");
        clickOn("#begin");
        for (int i = 0; i < 3; i++) {
            clickOn("#door1");
            GameModel gameModel = controller.getGameModel();
            RoomController room = controller.getGameModel().getMaze().getTail().getData();
            Scene scene = room.getScene();
            Button snake = (Button) scene.lookup("#snake");
            while (snake != null && gameModel.getHealth() > 0) {
                clickOn("#snake");
                snake = (Button) scene.lookup("#snake");
            }
            clickOn("#droppedItem");
        }
        GameModel gameModel = controller.getGameModel();
        RoomController room = controller.getGameModel().getMaze().getTail().getData();
        for (int i = 1; i < 3; i++) {
            Menu inventory = room.getPlayerMenu();
            inventory.setId("inventory");
            MenuItem item = inventory.getItems().get(i);
            item.setId("item");
            clickOn("#inventory").clickOn("#item");
        }
    }

    @Test
    public void testAttackPotion() throws Exception {
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
                if (item.getText().equalsIgnoreCase("attack potion")) {
                    int oldAttackPotion = controller.getGameModel().getAttackPotionActive();
                    item.setId("item");
                    clickOn("#inventory").clickOn("#item");
                    System.out.println(controller.getGameModel().getAttackPotionActive());
                    assertEquals(oldAttackPotion + 5,
                            controller.getGameModel().getAttackPotionActive());
                }
            }
            clickOn("#door1");
        }
    }
    
    @Test
    public void testShovelDamage() throws Exception {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("testShovelDamage");
        clickOn("#longSwordButton");
        clickOn("#begin");
        clickOn("#door1");
        boolean found = false;
        while (!found) {
            for (int i = 0; i < 9; i++) {
                RoomController room = controller.getGameModel().getMaze().getTail().getData();
                GameModel gameModel = controller.getGameModel();
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
                    if (item.getText().equalsIgnoreCase("shovel")) {
                        found = true;
                        if (i == 8) {
                            break;
                        }
                        item.setId("item");
                        clickOn("#inventory").clickOn("#item");
                        for (int currRoom = i; currRoom >= 0; currRoom--) {
                            clickOn("#goBack");
                        }
                        clickOn("#door2");
                        room = controller.getGameModel().getMaze().getTail().getData();
                        scene = room.getScene();
                        snake = (Button) scene.lookup("#snake");
                        clickOn("#snake");
                        int damage = gameModel.getAttackValue();
                        assertTrue("Error, damage is too low", damage >= 1);
                        assertTrue("Error, damage is too high", damage <= 3);

                    }
                } if (found) {
                    break;
                }
                clickOn("#door1");
            }
            found = true;
            System.out.println("No shovel spawned this game. Run the test again");
        }
    }
    
    @Test
    public void testArmor() throws Exception {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("testArmor");
        clickOn("#longSwordButton");
        clickOn("#begin");
        clickOn("#door1");
        boolean found = false;
        while (!found) {
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
                    if (item.getText().equalsIgnoreCase("armor")) {
                        found = true;
                        if (i == 8) {
                            break;
                        }
                        int oldHealth = controller.getGameModel().getHealth();
                        item.setId("item");
                        clickOn("#inventory").clickOn("#item");
                        for (int currRoom = i; currRoom >= 0; currRoom--) {
                            clickOn("#goBack");
                        }
                        clickOn("#door2");
                        room = controller.getGameModel().getMaze().getTail().getData();
                        scene = room.getScene();
                        snake = (Button) scene.lookup("#snake");
                        clickOn("#snake");
                        int newHealth = controller.getGameModel().getHealth();
                        assertEquals(oldHealth, newHealth);
                    }
                } if (found) {
                    break;
                }
                clickOn("#door1");
            }
            found = true;
            System.out.println("No armor spawned this game. Run the test again");
        }
    }


}
