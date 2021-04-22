package game.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class FinalBossController {

    @FXML
    private Text enterGold;

    @FXML
    private Button door;

    @FXML
    private Button snake;

    @FXML
    private Text enterHealth;

    @FXML
    private AnchorPane snakePane;

    @FXML
    private MenuBar menuBar;

    private Scene scene;
    private int height;
    private int width;
    private int snakeHealth;

    public FinalBossController() {

    }

    public FinalBossController(int width, int height) throws IOException {
        this.width = width;
        this.height = height;
        this.scene = new Scene(FXMLLoader.load(getClass().getResource("/game/screens/FinalBoss.fxml")));
        this.enterGold = (Text) scene.lookup("#enterGold");
        this.snakePane = (AnchorPane) scene.lookup("#snakePane");
        this.snake = (Button) scene.lookup("#snake");
        this.enterHealth = (Text) scene.lookup("#enterHealth");
        this.menuBar = (MenuBar) scene.lookup("#menuBar");
        this.door = (Button) scene.lookup("#door");
        this.snakeHealth = 100;
    }

    public Scene getScene() throws Exception {
        return this.scene;
    }

    public Text getEnterGold() {
        return this.enterGold;
    }

    public Button getDoor() {
        return this.door;
    }

    public Button getSnake() {
        return snake;
    }

    public Text getEnterHealth() {
        return enterHealth;
    }

    public void setEnterHealth(Text enterHealth) {
        this.enterHealth = enterHealth;
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

    public void setPlayerMenu(Menu inventory) {
        this.menuBar.getMenus().remove(0);
        this.menuBar.getMenus().add(0, inventory);
    }

    public int getSnakeHealth() {
        return this.snakeHealth;
    }

    public void dealDamage(int attack) {
        this.snakeHealth -= attack;
        System.out.println("Dealt " + attack + " damage to boss.");
        System.out.println("Boss now has " + snakeHealth + " health.");
    }

}
