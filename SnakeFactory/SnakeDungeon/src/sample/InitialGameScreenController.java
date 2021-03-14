package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;

public class InitialGameScreenController {

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

    private Scene scene;
    private int height;
    private int width;

    public InitialGameScreenController() {

    }

    public InitialGameScreenController(int width, int height) throws IOException {
        this.width = width;
        this.height = height;
        this.scene = new Scene(FXMLLoader.load(getClass().getResource("InitialGameScreen.fxml")));
        this.enterGold = (Text) scene.lookup("#enterGold");
        this.door1 = (Button) scene.lookup("#door1");
        this.door2 = (Button) scene.lookup("#door2");
        this.door3 = (Button) scene.lookup("#door3");
        this.door4 = (Button) scene.lookup("#door4");
    }

    public Scene getScene() throws Exception {
        return this.scene;
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

    public Text getEnterGold() {
        return this.enterGold;
    }
}
