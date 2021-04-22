package game.controllers;

import game.support.GameModel;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class MainController extends Application {

    private Stage mainWindow;
    private GameModel gameModel;

    private final int width = 500;
    private final int height = 500;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainWindow = primaryStage;
        mainWindow.setTitle("Snake Dungeon");
        initWelcomeScreen();
    }

    public void initWelcomeScreen() throws Exception {
        System.out.println("Welcome");
        WelcomeScreenController welcomeScreenController =
                new WelcomeScreenController(width, height);
        Scene scene = welcomeScreenController.getScene();
        Button startButton = (Button) scene.lookup("#startButton");
        startButton.setOnAction(e -> {
            try {
                goToConfigScreen();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        mainWindow.setScene(scene);
        mainWindow.show();
    }

    private void goToConfigScreen() throws Exception {
        ConfigScreenController configScreenController = new ConfigScreenController(width, height);
        Scene scene = configScreenController.getScene();
        TextField farmerName = (TextField) scene.lookup("#farmerName");
        Slider difficultySlider = (Slider) scene.lookup("#difficultySlider");
        //Text difficulty = (Text) scene.lookup("#difficulty");
        //difficulty.setText("Choose difficulty");
        Button longSwordButton = (Button) scene.lookup("#longSwordButton");
        Button maceButton = (Button) scene.lookup("#maceButton");
        Button daggerButton = (Button) scene.lookup("#daggerButton");
        Button begin = (Button) scene.lookup("#begin");

        AtomicReference<String> weaponSelected = new AtomicReference<>("");
        longSwordButton.setOnAction(e -> weaponSelected.set("Long Sword"));
        maceButton.setOnAction(e -> weaponSelected.set("Mace"));
        daggerButton.setOnAction(e -> weaponSelected.set("Dagger"));

        // will need to change difficulty text to change as slider does
        // utilize slider helper to get text equivalent to slider double value

        begin.setOnAction(e -> {
            String name = farmerName.getText();
            String diff = difficultySliderHelper(difficultySlider.getValue());
            int startingGold = 500 - (int) difficultySlider.getValue();
            int health = 100;
            if ((int) difficultySlider.getValue() == 100) {
                health = 5;
            }
            if (checkNameHelper(name, weaponSelected)) {
                gameModel = new GameModel(name, diff, weaponSelected.get(), startingGold, health);
                System.out.println(gameModel.toString());
                try {
                    RoomController roomController =
                            new RoomController(width,
                                    height, "/game/screens/InitialGameScreen.fxml");
                    gameModel.createMaze(roomController);
                    goToRoom(roomController);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        mainWindow.setScene(scene);
        mainWindow.show();
    }

    private void goToRoom(RoomController roomController) throws Exception {
        Scene scene = roomController.getScene();

        System.out.println(gameModel.getMaze().getSize());

        Text gold = roomController.getEnterGold();
        Text health = roomController.getEnterHealth();
        Button door1 = roomController.getDoor1();
        Button door2 = roomController.getDoor2();
        Button door3 = roomController.getDoor3();
        Button door4 = roomController.getDoor4();
        Button goBack = roomController.getGoBack();
        Button snake = roomController.getSnake();
        roomController.setPlayerMenu(buildInventory(gameModel.getInventoryString()));
        Menu inventory = roomController.getPlayerMenu();
        AnchorPane snakePane = roomController.getSnakePane();
        AtomicBoolean snakeDead = new AtomicBoolean(false);

        gold.setText(gameModel.getTotalGold() + " Gold Coins");
        health.setText(gameModel.getHealth() + " Health");

        if (roomController.getIsStart()) {
            snakePane.getChildren().remove(snake);
        }

        /* Attack mechanisms */

        if (!roomController.getIsStart()) {
            snake.setOnAction(e -> {
                if (roomController.getSnakeHealth() > 0) {
                    attackSnakeHelper(roomController);
                }
                if (gameModel.getHealth() <= 0) {
                    try {
                        goToFinalScreen(false);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                if (roomController.getSnakeHealth() <= 0) {
                    snakePane.getChildren().remove(snake);
                    snakeDead.set(true);
                    gameModel.addKill();
                    String droppedItem = dropItem(roomController);
                    Button newItem = new Button(droppedItem);
                    newItem.setId("droppedItem");
                    snakePane.getChildren().add(newItem);

                    newItem.setOnAction(i -> {
                        addItem(roomController, inventory, droppedItem);
                        snakePane.getChildren().remove(newItem);
                    });

                    if (true) {
                        door1.setOnAction(door1Action());
                    }
                    if (door2 != null) {
                        door2.setOnAction(door2Action());
                    }
                    if (door3 != null) {
                        door3.setOnAction(door3Action());
                    }
                    if (door4 != null) {
                        door4.setOnAction(door4Action());
                    }
                }
            });
        }

        /* Walk through doors mechanism */

        if (snakeDead.get() || roomController.getIsStart()) {
            door1.setOnAction(door1Action());
        }

        if (door2 != null && (snakeDead.get() || roomController.getIsStart())) {
            door2.setOnAction(door2Action());
        }

        if (door3 != null && (snakeDead.get() || roomController.getIsStart())) {
            door3.setOnAction(door3Action());
        }

        if (door4 != null && (snakeDead.get() || roomController.getIsStart())) {
            door4.setOnAction(door4Action());
        }

        goBack.setOnAction(e -> {
            try {
                goToRoom(gameModel.getMaze().goToPrevious().getData());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        inventory.setOnAction(inventoryInteraction());

        mainWindow.setScene(scene);
        mainWindow.show();
    }

    private void goToChallengeRoom(RoomController roomController) throws Exception {
        ChallengeRoomController challengeRoomController = new ChallengeRoomController(500, 500);
        Scene scene = challengeRoomController.getScene();

        Text gold = challengeRoomController.getEnterGold();
        Text health = challengeRoomController.getEnterHealth();
        Button door = challengeRoomController.getDoor();
        Button snake1 = challengeRoomController.getSnake1();
        Button snake2 = challengeRoomController.getSnake2();
        Button snake3 = challengeRoomController.getSnake3();
        Button snake4 = challengeRoomController.getSnake4();
        Button accept = challengeRoomController.getAccept();
        Button decline = challengeRoomController.getDecline();
        challengeRoomController.setPlayerMenu(buildInventory(gameModel.getInventoryString()));
        Menu inventory = challengeRoomController.getPlayerMenu();

        gold.setText(gameModel.getTotalGold() + " Gold Coins");
        health.setText(gameModel.getHealth() + " Health");

        challengeRoomController.setMenuMessage("4 wild snakes have appeared.");

        /* Walk through doors mechanism */



        accept.setOnAction(e -> {
            challengeRoomController.removeMenuMessage();
            snake1.setVisible(true);
            snake2.setVisible(true);
            snake3.setVisible(true);
            snake4.setVisible(true);
            accept.setVisible(false);
            decline.setVisible(false);
        });

        decline.setOnAction(e -> {
            try {
                if (roomController == null) {
                    goToFinalBoss();
                } else {
                    goToRoom(roomController);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        snake1.setOnAction(challengeAttackHelper(challengeRoomController, 1));
        snake2.setOnAction(challengeAttackHelper(challengeRoomController, 2));
        snake3.setOnAction(challengeAttackHelper(challengeRoomController, 3));
        snake4.setOnAction(challengeAttackHelper(challengeRoomController, 4));

        door.setOnAction(e -> {
            try {
                if (challengeRoomController.allSnakeDead()) {
                    if (roomController == null) {
                        gameModel.setHealth(100);
                        goToFinalBoss();
                    } else {
                        gameModel.setHealth(100);
                        goToRoom(roomController);
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        inventory.setOnAction(inventoryInteraction());

        mainWindow.setScene(scene);
        mainWindow.show();
    }

    private void goToFinalScreen(boolean win) throws Exception {
        EndGameController endGameController = new EndGameController();
        Scene scene = endGameController.getScene();
        Button restart = endGameController.getRestart();
        Button quit = endGameController.getQuit();
        if (!win) {
            endGameController.setCongratsText();
            endGameController.setMessageText();
        }
        endGameController.setTimeText(gameModel);
        endGameController.setKilledText(gameModel);
        endGameController.setDamageText(gameModel);

        restart.setOnAction(e -> {
            try {
                initWelcomeScreen();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        quit.setOnAction(m -> {
            Platform.exit();
        });
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    private void goToFinalBoss() throws Exception {
        FinalBossController finalBossController = new FinalBossController(500, 500);
        Scene scene = finalBossController.getScene();
        Text gold = finalBossController.getEnterGold();
        Text health = finalBossController.getEnterHealth();
        Button door = finalBossController.getDoor();
        Button snake = finalBossController.getSnake();
        finalBossController.setPlayerMenu(buildInventory(gameModel.getInventoryString()));
        Menu inventory = finalBossController.getPlayerMenu();
        AnchorPane snakePane = finalBossController.getSnakePane();
        AtomicBoolean snakeDead = new AtomicBoolean(false);

        snake.setOnAction(e -> {
            if (finalBossController.getSnakeHealth() > 0) {
                attackFinalBossHelper(finalBossController);
            }
            if (gameModel.getHealth() <= 0) {
                try {
                    goToFinalScreen(false);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            if (finalBossController.getSnakeHealth() <= 0) {
                snakePane.getChildren().remove(snake);
                snakeDead.set(true);
                gameModel.addKill();
            }
        });

        door.setOnAction(e -> {
            if (snakeDead.get()) {
                try {
                    goToFinalScreen(true);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        gold.setText(gameModel.getTotalGold() + " Gold Coins");
        health.setText(gameModel.getHealth() + " Health");

        mainWindow.setScene(scene);
        mainWindow.show();
    }

    /* MainController helper functions */

    private Menu buildInventory(ArrayList<String> inventoryString) {
        Menu inventory = new Menu("Inventory");
        for (String itemStr: inventoryString) {
            MenuItem item = new MenuItem(itemStr);
            inventory.getItems().add(item);
        }
        return inventory;
    }

    private void addItem(RoomController roomController, Menu inventory, String item) {
        inventory.getItems().add(new MenuItem(item));
        gameModel.getInventoryString().add(item);
        roomController.setPlayerMenu(inventory);
        String mess = gameModel.getFarmerName() + ": " + item + " added to inventory.";
        roomController.setMenuMessage(mess);
    }

    private String dropItem(RoomController roomController) {
        String item;
        Random rand = new Random();
        int num = rand.nextInt(13);
        switch (num) {
        case 0:
            item = "Attack Potion";
            break;
        case 1:
            item = "Attack Potion";
            break;
        case 2:
            item = "Attack Potion";
            break;
        case 3:
            item = "Shovel";
            break;
        case 4:
            item = "Bow";
            break;
        case 5:
            item = "Steel Dagger";
            break;
        case 6:
            item = "Shovel";
            break;
        case 7:
            item = "Diamond Sword";
            break;
        case 8:
            item = "Axe";
            break;
        case 9:
            item = "Armor";
            break;
        default:
            item = "Health Potion";
            break;
        }

        String message = "Snake Dungeon: " + item + " dropped from enemy";
        roomController.setMenuMessage(message);

        return item;
    }

    private EventHandler<ActionEvent> inventoryInteraction() {
        return new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                Menu inventory = (Menu) event.getSource();
                for (MenuItem item : inventory.getItems()) {
                    item.setOnAction(inventoryHelper(inventory));
                }
            }
        };
    }

    private EventHandler<ActionEvent> door1Action() {
        return new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                RoomController room = goThroughDoor1Helper();
                if (room == null) {
                    try {
                        goToChallengeRoom(null);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                } else if (gameModel.getMaze().getSize() == 5) {
                    try {
                        goToChallengeRoom(room);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        goToRoom(room);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        };
    }

    private EventHandler<ActionEvent> door2Action() {
        return new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                RoomController room = goThroughDoor2Helper();
                if (room == null) {
                    try {
                        goToChallengeRoom(null);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                } else if (gameModel.getMaze().getSize() == 5) {
                    try {
                        goToChallengeRoom(room);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        goToRoom(room);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        };
    }

    private EventHandler<ActionEvent> door3Action() {
        return new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                RoomController room = goThroughDoor3Helper();
                if (room == null) {
                    try {
                        goToChallengeRoom(null);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                } else if (gameModel.getMaze().getSize() == 5) {
                    try {
                        goToChallengeRoom(room);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        goToRoom(room);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        };
    }

    private EventHandler<ActionEvent> door4Action() {
        return new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                RoomController room = goThroughDoor4Helper();
                if (room == null) {
                    try {
                        goToChallengeRoom(null);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                } else if (gameModel.getMaze().getSize() == 5) {
                    try {
                        goToChallengeRoom(room);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        goToRoom(room);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        };
    }

    private EventHandler<ActionEvent> challengeAttackHelper(
            ChallengeRoomController room, int snake) {
        return new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                int attack = gameModel.getAttackValue();
                if (gameModel.getAttackPotionActive() > 0) {
                    attack += 5;
                    gameModel.decrementAttackPotion();
                }

                if (snake == 1) {
                    room.dealDamage(attack, 1);
                    if (room.getSnake1Health() <= 0) {
                        room.getSnakePane().getChildren().remove(room.getSnake1());
                        room.setSnake1Dead();
                        gameModel.addKill();
                    }
                } else if (snake == 2) {
                    room.dealDamage(attack, 2);
                    if (room.getSnake2Health() <= 0) {
                        room.getSnakePane().getChildren().remove(room.getSnake2());
                        room.setSnake2Dead();
                        gameModel.addKill();
                    }
                } else if (snake == 3) {
                    room.dealDamage(attack, 3);
                    if (room.getSnake3Health() <= 0) {
                        room.getSnakePane().getChildren().remove(room.getSnake3());
                        room.setSnake3Dead();
                        gameModel.addKill();
                    }
                } else if (snake == 4) {
                    room.dealDamage(attack, 4);
                    if (room.getSnake4Health() <= 0) {
                        room.getSnakePane().getChildren().remove(room.getSnake4());
                        room.setSnake4Dead();
                        gameModel.addKill();
                    }
                }
                int damage = 1;
                if (gameModel.getArmorActive() > 0) {
                    damage = 0;
                    gameModel.decrementArmor();
                }
                gameModel.addAttackDealt(attack);
                Text health = room.getEnterHealth();
                health.setText(gameModel.dealDamage(damage) + " Health");
            }
        };
    }

    private EventHandler<ActionEvent> inventoryHelper(Menu inventory) {
        return new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                MenuItem item = (MenuItem) event.getSource();
                String itemStr = item.getText();
                if ("health potion".equalsIgnoreCase(itemStr)) {
                    consumeHealthPotion(inventory, item);
                    System.out.println("Consumed Health Potion.\nHealth restored");
                } else if ("attack potion".equalsIgnoreCase(itemStr)) {
                    consumeAttackPotion(inventory, item);
                    System.out.println("Consumed Attack Potion.\nAttack buffed.");
                } else if ("armor".equalsIgnoreCase(itemStr)) {
                    equipArmor(inventory, item);
                    System.out.println("Armor Equipped.\nDefense increased.");
                } else {
                    equipWeapon(itemStr);
                    System.out.println(itemStr + " equipped.");
                }
            }
        };
    }

    private String difficultySliderHelper(double value) {
        int test = (int) value / 25;
        String temp = null;
        switch (test) {
        case 0:
            temp = "Easy";
            break;
        case 1:
            temp = "Normal";
            break;
        case 2:
            temp = "Hard";
            break;
        case 3:
            temp = "Legendary";
            break;
        case 4:
            temp = "X-Games";
            break;
        default:
            temp = "Easy";
        }
        return temp;
    }

    private boolean checkNameHelper(String name, AtomicReference<String> weaponSelected) {
        int spaces = 0;
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == ' ') {
                spaces++;
            }
        }
        return spaces != name.length() && name != null
                && !name.isEmpty() && !weaponSelected.get().isEmpty();
    }

    private RoomController goThroughDoor1Helper() {
        RoomController room = null;
        try {
            if (gameModel.getMaze().getTail().getFirst() == null
                    && gameModel.getMaze().getSize() < 10) {
                room = new RoomController(width, height);
                gameModel.getMaze().addToFirst(room);
            } else if (gameModel.getMaze().getSize() < 10) {
                room = gameModel.getMaze().getTail().getFirst().getData();
                gameModel.getMaze().goToFirst();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return room;
    }

    private RoomController goThroughDoor2Helper() {
        RoomController room = null;
        try {
            if (gameModel.getMaze().getTail().getSecond() == null
                    && gameModel.getMaze().getSize() < 10) {
                room = new RoomController(width, height);
                gameModel.getMaze().addToSecond(room);
            } else if (gameModel.getMaze().getSize() < 10) {
                room = gameModel.getMaze().getTail().getSecond().getData();
                gameModel.getMaze().goToSecond();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return room;
    }

    private RoomController goThroughDoor3Helper() {
        RoomController room = null;
        try {
            if (gameModel.getMaze().getTail().getThird() == null
                    && gameModel.getMaze().getSize() < 10) {
                room = new RoomController(width, height);
                gameModel.getMaze().addToThird(room);
            } else if (gameModel.getMaze().getSize() < 10) {
                room = gameModel.getMaze().getTail().getThird().getData();
                gameModel.getMaze().goToThird();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return room;
    }

    private RoomController goThroughDoor4Helper() {
        RoomController room = null;
        try {
            if (gameModel.getMaze().getTail().getFourth() == null
                    && gameModel.getMaze().getSize() < 10) {
                room = new RoomController(width, height);
                gameModel.getMaze().addToFourth(room);
            } else if (gameModel.getMaze().getSize() < 10) {
                room = gameModel.getMaze().getTail().getFourth().getData();
                gameModel.getMaze().goToFourth();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return room;
    }

    private void attackSnakeHelper(RoomController room) {
        int attack = gameModel.getAttackValue();
        if (gameModel.getAttackPotionActive() > 0) {
            attack += 5;
            gameModel.decrementAttackPotion();
        }
        room.dealDamage(attack);
        gameModel.addAttackDealt(attack);
        Text health = room.getEnterHealth();
        int damage = 1;
        if (gameModel.getArmorActive() > 0) {
            damage = 0;
            gameModel.decrementArmor();
        }
        health.setText(gameModel.dealDamage(damage) + " Health");
    }

    private void attackFinalBossHelper(FinalBossController room) {
        int attack = gameModel.getAttackValue();
        if (gameModel.getAttackPotionActive() > 0) {
            attack += 5;
            gameModel.decrementAttackPotion();
        }
        room.dealDamage(attack);
        gameModel.addAttackDealt(attack);
        Text health = room.getEnterHealth();
        int damage = 3;
        if (gameModel.getArmorActive() > 0) {
            damage = 0;
            gameModel.decrementArmor();
        }
        health.setText(gameModel.dealDamage(damage) + " Health");
    }

    private void consumeHealthPotion(Menu inventory, MenuItem potion) {
        RoomController roomController = getGameModel().getMaze().getTail().getData();
        int currentHealth = gameModel.getHealth();
        int newHealth = currentHealth + 10;
        if (newHealth > 100) {
            newHealth = newHealth + (100 - newHealth);
        }
        gameModel.setHealth(newHealth);
        roomController.setMenuMessage(gameModel.getFarmerName() + " : Health restored.");
        Text health = roomController.getEnterHealth();
        health.setText(gameModel.getHealth() + " Health");

        inventory.getItems().remove(potion);
        gameModel.getInventoryString().remove(potion.getText());
        roomController.setPlayerMenu(inventory);
    }

    private void consumeAttackPotion(Menu inventory, MenuItem potion) {
        gameModel.setAttackPotionActive(5);
        RoomController roomController = getGameModel().getMaze().getTail().getData();
        roomController.setMenuMessage(gameModel.getFarmerName()
                + " : Attack buffed for 5 attacks.");

        inventory.getItems().remove(potion);
        gameModel.getInventoryString().remove(potion.getText());
        roomController.setPlayerMenu(inventory);
    }

    private void equipArmor(Menu inventory, MenuItem armor) {
        RoomController roomController = getGameModel().getMaze().getTail().getData();
        gameModel.setArmorActive(5);
        roomController.setMenuMessage(gameModel.getFarmerName()
                + " : Defense increased for 5 attacks.");

        inventory.getItems().remove(armor);
        gameModel.getInventoryString().remove(armor.getText());
        roomController.setPlayerMenu(inventory);
    }

    private void equipWeapon(String newWeapon) {
        RoomController roomController = getGameModel().getMaze().getTail().getData();
        gameModel.changeWeapon(newWeapon);
        roomController.setMenuMessage(gameModel.getFarmerName() + " : " + newWeapon + " equipped.");
    }

    /* MainController getter & setter functions */

    public GameModel getGameModel() {
        return gameModel;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
