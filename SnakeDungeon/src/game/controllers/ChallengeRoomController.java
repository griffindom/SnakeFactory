package game.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class ChallengeRoomController {

    @FXML
    private Text enterGold;

    @FXML
    private Button door;

    @FXML
    private Button accept;

    @FXML
    private Button decline;

    @FXML
    private Button snake1;

    @FXML
    private Button snake2;

    @FXML
    private Button snake3;

    @FXML
    private Button snake4;

    @FXML
    private Text enterHealth;

    @FXML
    private AnchorPane snakePane;

    @FXML
    private MenuBar menuBar;

    private Scene scene;
    private int height;
    private int width;
    private int snakeHealth1;
    private int snakeHealth2;
    private int snakeHealth3;
    private int snakeHealth4;
    private boolean snake1Dead;
    private boolean snake2Dead;
    private boolean snake3Dead;
    private boolean snake4Dead;

    public ChallengeRoomController() {

    }

    public ChallengeRoomController(int width, int height) throws IOException {
        this.width = width;
        this.height = height;
        this.scene = new Scene(FXMLLoader.load(
                getClass().getResource("/game/screens/ChallengeRoom.fxml")));
        this.enterGold = (Text) scene.lookup("#enterGold");
        this.accept = (Button) scene.lookup("#accept");
        this.decline = (Button) scene.lookup("#decline");
        this.snakePane = (AnchorPane) scene.lookup("#snakePane");
        this.enterHealth = (Text) scene.lookup("#enterHealth");
        this.menuBar = (MenuBar) scene.lookup("#menuBar");
        this.door = (Button) scene.lookup("#door");
        this.snake1 = (Button) scene.lookup("#snake1");
        this.snake2 = (Button) scene.lookup("#snake2");
        this.snake3 = (Button) scene.lookup("#snake3");
        this.snake4 = (Button) scene.lookup("#snake4");
        this.snakeHealth1 = 20;
        this.snakeHealth2 = 20;
        this.snakeHealth3 = 20;
        this.snakeHealth4 = 20;
        this.snake1Dead = false;
        this.snake2Dead = false;
        this.snake3Dead = false;
        this.snake4Dead = false;
    }

    public Scene getScene() throws Exception {
        return this.scene;
    }

    public void setSnake1Dead() {
        this.snake1Dead = true;
    }

    public void setSnake2Dead() {
        this.snake2Dead = true;
    }

    public void setSnake3Dead() {
        this.snake3Dead = true;
    }

    public void setSnake4Dead() {
        this.snake4Dead = true;
    }

    public boolean allSnakeDead() {
        return snake1Dead && snake2Dead && snake3Dead && snake4Dead;
    }

    public Button getDoor() {
        return this.door;
    }

    public Text getEnterGold() {
        return this.enterGold;
    }

    public Button getSnake1() {
        return this.snake1;
    }

    public Button getSnake2() {
        return this.snake2;
    }

    public Button getSnake3() {
        return this.snake3;
    }

    public Button getSnake4() {
        return this.snake4;
    }

    public Button getAccept() {
        return this.accept;
    }

    public Button getDecline() {
        return this.decline;
    }

    public Text getEnterHealth() {
        return enterHealth;
    }

    public void setEnterHealth(Text enterHealth) {
        this.enterHealth = enterHealth;
    }

    public int getSnake1Health() {
        return this.snakeHealth1;
    }

    public int getSnake2Health() {
        return this.snakeHealth2;
    }

    public int getSnake3Health() {
        return this.snakeHealth3;
    }

    public int getSnake4Health() {
        return this.snakeHealth4;
    }

    public AnchorPane getSnakePane() {
        return snakePane;
    }

    public MenuBar getMenuBar() {
        return this.menuBar;
    }

    public Menu getPlayerMenu() {
        return this.menuBar.getMenus().get(0);
    }

    public void setMenuMessage(String message) {
        if (this.menuBar.getMenus().size() > 1) {
            this.menuBar.getMenus().remove(1);
        }
        this.menuBar.getMenus().add(new Menu(message));
    }

    public void removeMenuMessage() {
        this.menuBar.getMenus().remove(1);
    }

    public void setPlayerMenu(Menu inventory) {
        this.menuBar.getMenus().remove(0);
        this.menuBar.getMenus().add(0, inventory);
    }

    public void dealDamage(int attack, int snake) {
        if (snake == 1) {
            this.snakeHealth1 -= attack;
            System.out.println("Dealt " + attack + " damage to snake one.");
            System.out.println("Snake one now has " + snakeHealth1 + " health.");
        } else if (snake == 2) {
            this.snakeHealth2 -= attack;
            System.out.println("Dealt " + attack + " damage to snake two.");
            System.out.println("Snake two now has " + snakeHealth2 + " health.");
        } else if (snake == 3) {
            this.snakeHealth3 -= attack;
            System.out.println("Dealt " + attack + " damage to snake three.");
            System.out.println("Snake three now has " + snakeHealth3 + " health.");
        } else if (snake == 4) {
            this.snakeHealth4 -= attack;
            System.out.println("Dealt " + attack + " damage to snake four.");
            System.out.println("Snake four now has " + snakeHealth4 + " health.");
        } else {
            System.out.println("Snake not found.");
        }
    }
}
