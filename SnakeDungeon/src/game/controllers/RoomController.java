package game.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import java.util.Random;

import java.io.IOException;

public class RoomController {

    @FXML
    private Text enterGold;

    @FXML
    private Button door1;

    @FXML
    private Button door2;

    @FXML
    private Button door3;

    @FXML
    private Button door4;

    @FXML
    private Button goBack;

    @FXML
    private Button snake;

    @FXML
    private Text enterHealth;

    @FXML
    private AnchorPane snakePane;

    private Scene scene;
    private int height;
    private int width;
    private String roomType;
    private int snakeHealth;
    private boolean isStart;

    public RoomController() {

    }

    public RoomController(int width, int height) throws IOException {
        this.width = width;
        this.height = height;
        this.scene = new Scene(FXMLLoader.load(getClass().getResource(this.randomScene())));
        this.enterGold = (Text) scene.lookup("#enterGold");
        this.goBack = (Button) scene.lookup("#goBack");
        this.snakePane = (AnchorPane) scene.lookup("#snakePane");
        this.isStart = false;
        this.enterHealth = (Text) scene.lookup("#enterHealth");
        this.initDoors();
    }

    public RoomController(int width, int height, String room) throws IOException {
        this.width = width;
        this.height = height;
        this.roomType = room;
        this.scene = new Scene(FXMLLoader.load(getClass().getResource(room)));
        this.enterGold = (Text) scene.lookup("#enterGold");
        this.goBack = (Button) scene.lookup("#goBack");
        this.snakePane = (AnchorPane) scene.lookup("#snakePane");
        this.enterHealth = (Text) scene.lookup("#enterHealth");
        this.isStart = true;
        this.initDoors();
    }

    public Scene getScene() throws Exception {
        return this.scene;
    }

    public Text getEnterGold() {
        return this.enterGold;
    }

    public Button getDoor1() {
        return this.door1;
    }

    public Button getDoor2() {
        return this.door2;
    }

    public Button getDoor3() {
        return this.door3;
    }

    public Button getDoor4() {
        return this.door4;
    }

    public Button getGoBack() {
        return this.goBack;
    }

    public Button getSnake() {
        return snake;
    }

    public void setSnake(Button snake) {
        this.snake = snake;
    }

    public Text getEnterHealth() {
        return enterHealth;
    }

    public void setEnterHealth(Text enterHealth) {
        this.enterHealth = enterHealth;
    }

    public int getSnakeHealth() {
        return this.snakeHealth;
    }

    public boolean getIsStart() {
        return this.isStart;
    }

    public void setSnakeHealth(int snakeHealth) {
        this.snakeHealth = snakeHealth;
    }

    public AnchorPane getSnakePane() {
        return snakePane;
    }

    public void dealDamage(int attack) {
        this.snakeHealth -= attack;
        System.out.println("Snake now has " + snakeHealth + " health.");
    }

    public String randomScene() {
        String[] rooms = {"/game/screens/1door.fxml", "/game/screens/2door.fxml",
            "/game/screens/3door.fxml", "/game/screens/4door.fxml"};
        Random rand = new Random();
        int room = rand.nextInt(4);
        this.roomType = rooms[room];
        return rooms[room];
    }

    public void initDoors() {
        switch (this.roomType) {
        case "/game/screens/1door.fxml":
            door1 = (Button) scene.lookup("#door1");
            door2 = null;
            door3 = null;
            door4 = null;
            snake = (Button) scene.lookup("#snake");
            this.snakeHealth = 20;
            break;
        case "/game/screens/2door.fxml":
            door1 = (Button) scene.lookup("#door1");
            door2 = (Button) scene.lookup("#door2");
            door3 = null;
            door4 = null;
            snake = (Button) scene.lookup("#snake");
            this.snakeHealth = 10;
            break;
        case "/game/screens/3door.fxml":
            door1 = (Button) scene.lookup("#door1");
            door2 = (Button) scene.lookup("#door2");
            door3 = (Button) scene.lookup("#door3");
            door4 = null;
            snake = (Button) scene.lookup("#snake");
            this.snakeHealth = 30;
            break;
        case "/game/screens/4door.fxml":
            door1 = (Button) scene.lookup("#door1");
            door2 = (Button) scene.lookup("#door2");
            door3 = (Button) scene.lookup("#door3");
            door4 = (Button) scene.lookup("#door4");
            snake = (Button) scene.lookup("#snake");
            this.snakeHealth = 40;
            break;
        case "/game/screens/InitialGameScreen.fxml":
            door1 = (Button) scene.lookup("#door1");
            door2 = (Button) scene.lookup("#door2");
            door3 = (Button) scene.lookup("#door3");
            door4 = (Button) scene.lookup("#door4");
            snake = (Button) scene.lookup("#snake");
            this.snakeHealth = 0;
            break;
        default:
            break;
        }
    }
}
