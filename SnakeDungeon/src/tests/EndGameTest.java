package tests;

import game.controllers.ChallengeRoomController;
import game.controllers.FinalBossController;
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

public class EndGameTest extends ApplicationTest {

    private MainController controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new MainController();
        controller.start(primaryStage);
    }

    @Test
    public void testChallengeNoRetreat() throws Exception {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("testChallengeNoRetreat");
        clickOn("#longSwordButton");
        clickOn("#begin");
        clickOn("#door1");
        for (int i = 0; i < 3; i++) {
            RoomController room = controller.getGameModel().getMaze().getTail().getData();
            GameModel gameModel = controller.getGameModel();
            Scene scene = room.getScene();
            Button snake = (Button) scene.lookup("#snake");
            while (snake != null) {
                clickOn("#snake");
                snake = (Button) scene.lookup("#snake");
            }
            clickOn("#door1");
        }
        clickOn("#accept");
        try {
            clickOn("#goBack");
        } catch (Exception e) {
            System.out.println("Cant go back");
        }
    }

    @Test
    public void testChallengeMultEnemies() throws Exception {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("testChallengeMultEnemies");
        clickOn("#longSwordButton");
        clickOn("#begin");
        clickOn("#door1");
        for (int i = 0; i < 3; i++) {
            RoomController room = controller.getGameModel().getMaze().getTail().getData();
            GameModel gameModel = controller.getGameModel();
            Scene scene = room.getScene();
            Button snake = (Button) scene.lookup("#snake");
            while (snake != null) {
                clickOn("#snake");
                snake = (Button) scene.lookup("#snake");
            }
            clickOn("#door1");
        }
        clickOn("#accept");
        GameModel gameModel = controller.getGameModel();
        ChallengeRoomController room = new ChallengeRoomController(500, 500);
        Scene scene = room.getScene();
        Button snake1 = (Button) room.getSnake1();
        System.out.println(snake1);
        Button snake2 = (Button) room.getSnake2();
        Button snake3 = (Button) room.getSnake3();
        Button snake4 = (Button) room.getSnake4();
        int snakesKilled = 0;
        boolean found1 = false;
        boolean found2 = false;
        boolean found3 = false;
        boolean found4 = false;
        while (!found1) {
            try {
                clickOn("#snake1");
            } catch (Exception e) {
                snakesKilled++;
                found1 = true;
            }
        }
        while (!found2) {
            try {
                clickOn("#snake2");
            } catch (Exception e) {
                snakesKilled++;
                found2 = true;
            }
        }
        while (!found3) {
            try {
                clickOn("#snake3");
            } catch (Exception e) {
                snakesKilled++;
                found3 = true;
            }
        }
        while (!found4) {
            try {
                clickOn("#snake4");
            } catch (Exception e) {
                snakesKilled++;
                found4 = true;
            }
        }
        assertEquals(snakesKilled, 4);
    }

    @Test
    public void testDeclineChallenge() throws Exception {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("testDeclineChallenge");
        clickOn("#longSwordButton");
        clickOn("#begin");
        clickOn("#door1");
        for (int i = 0; i < 3; i++) {
            RoomController room = controller.getGameModel().getMaze().getTail().getData();
            GameModel gameModel = controller.getGameModel();
            Scene scene = room.getScene();
            Button snake = (Button) scene.lookup("#snake");
            while (snake != null) {
                clickOn("#snake");
                snake = (Button) scene.lookup("#snake");
            }
            clickOn("#door1");
        }
        clickOn("#decline");
    }

    @Test
    public void testMultChallengeRooms() throws Exception {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("testMultChallengeRooms");
        clickOn("#longSwordButton");
        clickOn("#begin");
        clickOn("#door1");
        for (int i = 0; i < 3; i++) {
            RoomController room = controller.getGameModel().getMaze().getTail().getData();
            GameModel gameModel = controller.getGameModel();
            Scene scene = room.getScene();
            Button snake = (Button) scene.lookup("#snake");
            while (snake != null) {
                clickOn("#snake");
                snake = (Button) scene.lookup("#snake");
            }
            clickOn("#door1");
        }
        clickOn("#accept");
        GameModel gameModel = controller.getGameModel();
        ChallengeRoomController room = new ChallengeRoomController(500, 500);
        Scene scene = room.getScene();
        Button snake1 = (Button) room.getSnake1();
        System.out.println(snake1);
        Button snake2 = (Button) room.getSnake2();
        Button snake3 = (Button) room.getSnake3();
        Button snake4 = (Button) room.getSnake4();
        int snakesKilled = 0;
        boolean found1 = false;
        boolean found2 = false;
        boolean found3 = false;
        boolean found4 = false;
        while (!found1) {
            try {
                clickOn("#snake1");
            } catch (Exception e) {
                snakesKilled++;
                found1 = true;
            }
        }
        while (!found2) {
            try {
                clickOn("#snake2");
            } catch (Exception e) {
                snakesKilled++;
                found2 = true;
            }
        }
        while (!found3) {
            try {
                clickOn("#snake3");
            } catch (Exception e) {
                snakesKilled++;
                found3 = true;
            }
        }
        while (!found4) {
            try {
                clickOn("#snake4");
            } catch (Exception e) {
                snakesKilled++;
                found4 = true;
            }
        }
        int counter = 1;
        clickOn("#door");
        for (int i = 0; i < 6; i++) {
            RoomController room2 = controller.getGameModel().getMaze().getTail().getData();
            GameModel gameModel2 = controller.getGameModel();
            Scene scene2 = room2.getScene();
            Button snake = (Button) scene2.lookup("#snake");
            while (snake != null) {
                clickOn("#snake");
                snake = (Button) scene2.lookup("#snake");
            }
            clickOn("#door1");
        }
        clickOn("#accept");
        GameModel gameModel3 = controller.getGameModel();
        ChallengeRoomController room3 = new ChallengeRoomController(500, 500);
        Scene scene3 = room3.getScene();
        Button tsnake1 = (Button) room.getSnake1();
        Button tsnake2 = (Button) room.getSnake2();
        Button tsnake3 = (Button) room.getSnake3();
        Button tsnake4 = (Button) room.getSnake4();
        boolean tfound1 = false;
        boolean tfound2 = false;
        boolean tfound3 = false;
        boolean tfound4 = false;
        while (!tfound1) {
            try {
                clickOn("#snake1");
            } catch (Exception e) {
                tfound1 = true;
            }
        }
        while (!tfound2) {
            try {
                clickOn("#snake2");
            } catch (Exception e) {
                tfound2 = true;
            }
        }
        while (!tfound3) {
            try {
                clickOn("#snake3");
            } catch (Exception e) {
                tfound3 = true;
            }
        }
        while (!tfound4) {
            try {
                clickOn("#snake4");
            } catch (Exception e) {
                tfound4 = true;
            }
        }
        counter++;
        assertEquals(counter, 2);
    }

    @Test
    public void testChallengeReward() throws Exception {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("testChallengeReward");
        clickOn("#longSwordButton");
        clickOn("#begin");
        clickOn("#door1");
        for (int i = 0; i < 3; i++) {
            RoomController room = controller.getGameModel().getMaze().getTail().getData();
            GameModel gameModel = controller.getGameModel();
            Scene scene = room.getScene();
            Button snake = (Button) scene.lookup("#snake");
            while (snake != null) {
                clickOn("#snake");
                snake = (Button) scene.lookup("#snake");
            }
            clickOn("#door1");
        }
        clickOn("#accept");
        GameModel gameModel = controller.getGameModel();
        ChallengeRoomController room = new ChallengeRoomController(500, 500);
        Scene scene = room.getScene();
        Button snake1 = (Button) room.getSnake1();
        System.out.println(snake1);
        Button snake2 = (Button) room.getSnake2();
        Button snake3 = (Button) room.getSnake3();
        Button snake4 = (Button) room.getSnake4();
        boolean found1 = false;
        boolean found2 = false;
        boolean found3 = false;
        boolean found4 = false;
        while (!found1) {
            try {
                clickOn("#snake1");
            } catch (Exception e) {
                found1 = true;
            }
        }
        while (!found2) {
            try {
                clickOn("#snake2");
            } catch (Exception e) {
                found2 = true;
            }
        }
        while (!found3) {
            try {
                clickOn("#snake3");
            } catch (Exception e) {
                found3 = true;
            }
        }
        while (!found4) {
            try {
                clickOn("#snake4");
            } catch (Exception e) {
                found4 = true;
            }
        }
        int health = controller.getGameModel().getHealth();
        clickOn("#door");
        health = controller.getGameModel().getHealth();
        assertEquals(health, 100);
    }

    @Test
    public void testBeatGame() throws Exception {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("testBeatGame");
        clickOn("#longSwordButton");
        clickOn("#begin");
        clickOn("#door1");
        for (int i = 0; i < 3; i++) {
            RoomController room = controller.getGameModel().getMaze().getTail().getData();
            GameModel gameModel = controller.getGameModel();
            Scene scene = room.getScene();
            Button snake = (Button) scene.lookup("#snake");
            while (snake != null) {
                clickOn("#snake");
                snake = (Button) scene.lookup("#snake");
            }
            clickOn("#door1");
        }
        clickOn("#decline");
        for (int i = 0; i < 6; i++) {
            RoomController room2 = controller.getGameModel().getMaze().getTail().getData();
            GameModel gameModel2 = controller.getGameModel();
            Scene scene2 = room2.getScene();
            Button snake = (Button) scene2.lookup("#snake");
            while (snake != null) {
                clickOn("#snake");
                snake = (Button) scene2.lookup("#snake");
            }
            clickOn("#door1");
        }
        clickOn("#decline");
        FinalBossController finalBossController = new FinalBossController(500, 500);
        Scene fscene = finalBossController.getScene();
        Button bossSnake = (Button) finalBossController.getSnake();
        boolean ffound = false;
        while (!ffound) {
            try {
                clickOn("#snake");
            } catch (Exception e) {
                ffound = true;
            }
        }
        clickOn("#door");
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
        clickOn("#restart");
    }
    
    @Test
    public void testRestartAfterGameOver() throws Exception {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("testBeatGame");
        clickOn("#longSwordButton");
        clickOn("#begin");
        clickOn("#door1");
        for (int i = 0; i < 3; i++) {
            RoomController room = controller.getGameModel().getMaze().getTail().getData();
            GameModel gameModel = controller.getGameModel();
            Scene scene = room.getScene();
            Button snake = (Button) scene.lookup("#snake");
            while (snake != null) {
                clickOn("#snake");
                snake = (Button) scene.lookup("#snake");
            }
            clickOn("#door1");
        }
        clickOn("#decline");
        for (int i = 0; i < 6; i++) {
            RoomController room2 = controller.getGameModel().getMaze().getTail().getData();
            GameModel gameModel2 = controller.getGameModel();
            Scene scene2 = room2.getScene();
            Button snake = (Button) scene2.lookup("#snake");
            while (snake != null) {
                clickOn("#snake");
                snake = (Button) scene2.lookup("#snake");
            }
            clickOn("#door1");
        }
        clickOn("#decline");
        FinalBossController finalBossController = new FinalBossController(500, 500);
        Scene fscene = finalBossController.getScene();
        Button bossSnake = (Button) finalBossController.getSnake();
        boolean ffound = false;
        while (!ffound) {
            try {
                clickOn("#snake");
            } catch (Exception e) {
                ffound = true;
            }
        }
        clickOn("#door");
        clickOn("#restart");
    }

    @Test
    public void testEndGame() throws Exception {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("testEndGame");
        clickOn("#longSwordButton");
        clickOn("#begin");
        clickOn("#door1");
        for (int i = 0; i < 3; i++) {
            RoomController room = controller.getGameModel().getMaze().getTail().getData();
            GameModel gameModel = controller.getGameModel();
            Scene scene = room.getScene();
            Button snake = (Button) scene.lookup("#snake");
            while (snake != null) {
                clickOn("#snake");
                snake = (Button) scene.lookup("#snake");
            }
            clickOn("#door1");
        }
        clickOn("#decline");
        for (int i = 0; i < 6; i++) {
            RoomController room2 = controller.getGameModel().getMaze().getTail().getData();
            GameModel gameModel2 = controller.getGameModel();
            Scene scene2 = room2.getScene();
            Button snake = (Button) scene2.lookup("#snake");
            while (snake != null) {
                clickOn("#snake");
                snake = (Button) scene2.lookup("#snake");
            }
            clickOn("#door1");
        }
        clickOn("#decline");
        FinalBossController finalBossController = new FinalBossController(500, 500);
        Scene fscene = finalBossController.getScene();
        Button bossSnake = (Button) finalBossController.getSnake();
        boolean ffound = false;
        while (!ffound) {
            try {
                clickOn("#snake");
            } catch (Exception e) {
                ffound = true;
            }
        }
        clickOn("#door");
    }

    @Test
    public void testDeathPopUp() throws Exception {
        clickOn("#startButton");
        clickOn("#farmerName");
        write("testDeathPopUp");
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
        verifyThat("You have failed to escape the Snake Dungeon!", NodeMatchers.isNotNull());
    }

}
