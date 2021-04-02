package game.controllers;

import game.support.GameModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
        System.out.println(roomController);
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
                attackSnakeHelper(roomController);
                if (gameModel.getHealth() <= 0) {
                    try {
                        goToConfigScreen();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                if (roomController.getSnakeHealth() <= 0) {
                    snakePane.getChildren().remove(snake);
                    snakeDead.set(true);

                    if (true) {
                        door1.setOnAction(m -> {
                            RoomController room = goThroughDoor1Helper();
                            if (room == null) {
                                try {
                                    goToFinalScreen();
                                } catch (Exception exception) {
                                    exception.printStackTrace();
                                }
                            } else {
                                try {
                                    goToRoom(room);
                                } catch (Exception exception) {
                                    exception.printStackTrace();
                                }
                            }
                        });
                    }

                    if (door2 != null) {
                        door2.setOnAction(m -> {
                            RoomController room = goThroughDoor2Helper();
                            if (room == null) {
                                try {
                                    goToFinalScreen();
                                } catch (Exception exception) {
                                    exception.printStackTrace();
                                }
                            } else {
                                try {
                                    goToRoom(room);
                                } catch (Exception exception) {
                                    exception.printStackTrace();
                                }
                            }
                        });
                    }

                    if (door3 != null) {
                        door3.setOnAction(m -> {
                            RoomController room = goThroughDoor3Helper();
                            if (room == null) {
                                try {
                                    goToFinalScreen();
                                } catch (Exception exception) {
                                    exception.printStackTrace();
                                }
                            } else {
                                try {
                                    goToRoom(room);
                                } catch (Exception exception) {
                                    exception.printStackTrace();
                                }
                            }
                        });
                    }

                    if (door4 != null) {
                        door4.setOnAction(m -> {
                            RoomController room = goThroughDoor4Helper();
                            if (room == null) {
                                try {
                                    goToFinalScreen();
                                } catch (Exception exception) {
                                    exception.printStackTrace();
                                }
                            } else {
                                try {
                                    goToRoom(room);
                                } catch (Exception exception) {
                                    exception.printStackTrace();
                                }
                            }
                        });
                    }
                }
            });
        }

        /* Walk through doors mechanism */

        if (snakeDead.get() || roomController.getIsStart()) {
            door1.setOnAction(e -> {
                RoomController room = goThroughDoor1Helper();
                if (room == null) {
                    try {
                        goToFinalScreen();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                } else {
                    try {
                        goToRoom(room);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            });
        }

        if (door2 != null && (snakeDead.get() || roomController.getIsStart())) {
            door2.setOnAction(e -> {
                RoomController room = goThroughDoor2Helper();
                if (room == null) {
                    try {
                        goToFinalScreen();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                } else {
                    try {
                        goToRoom(room);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            });
        }

        if (door3 != null && (snakeDead.get() || roomController.getIsStart())) {
            door3.setOnAction(e -> {
                RoomController room = goThroughDoor3Helper();
                if (room == null) {
                    try {
                        goToFinalScreen();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                } else {
                    try {
                        goToRoom(room);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            });
        }

        if (door4 != null && (snakeDead.get() || roomController.getIsStart())) {
            door4.setOnAction(e -> {
                RoomController room = goThroughDoor4Helper();
                if (room == null) {
                    try {
                        goToFinalScreen();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                } else {
                    try {
                        goToRoom(room);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            });
        }

        goBack.setOnAction(e -> {
            try {
                goToRoom(gameModel.getMaze().goToPrevious().getData());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        mainWindow.setScene(scene);
        mainWindow.show();
    }

    /* MainController helper functions */

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

    private void goToFinalScreen() throws Exception {
        Scene scene = new
                Scene(FXMLLoader.load(getClass().getResource("/game/screens/EndScreen.fxml")));
        mainWindow.setScene(scene);
        mainWindow.show();
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
        room.dealDamage(gameModel.getAttackValue());
        Text health = room.getEnterHealth();
        health.setText(gameModel.dealDamage(1) + " Health");
    }

    /* MainController getter & setter functions */

    public GameModel getGameModel() {
        return gameModel;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
