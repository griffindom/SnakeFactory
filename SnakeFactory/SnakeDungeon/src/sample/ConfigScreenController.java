package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

public class ConfigScreenController {

    @FXML
    private TextField farmerName;
    @FXML
    private Slider difficultySlider;
    @FXML
    private Text difficulty;
    @FXML
    private Button longSwordButton;
    @FXML
    private Button maceButton;
    @FXML
    private Button daggerButton;
    @FXML
    private Button begin;

    private Scene scene;
    private int height;
    private int width;

    public ConfigScreenController() { }

    public ConfigScreenController(int width, int height) throws IOException {
        this.width = width;
        this.height = height;
        this.scene = new Scene(FXMLLoader.load(getClass().getResource("ConfigGameScreen.fxml")));
        this.farmerName = (TextField) scene.lookup("#farmerName");
        this.difficultySlider = (Slider) scene.lookup("#difficultySlider");
        this.difficulty = (Text) scene.lookup("#difficulty");
        this.longSwordButton = (Button) scene.lookup("#longSwordButton");
        this.maceButton = (Button) scene.lookup("#maceButton");
        this.daggerButton = (Button) scene.lookup("#daggerButton");
        this.begin = (Button) scene.lookup("#begin");
    }

    public Scene getScene() throws Exception {
        return this.scene;
    }

    public TextField getFarmerName() {
        return this.farmerName;
    }

    public Slider getDifficultySlider() {
        return this.difficultySlider;
    }

    public Text getDifficulty() {
        return this.difficulty;
    }

    public Button getLongSwordButton() {
        return this.longSwordButton;
    }

    public Button getMaceButton() {
        return this.maceButton;
    }

    public Button getDaggerButton() {
        return this.daggerButton;
    }

    public Button getBegin() {
        return this.begin;
    }

}
