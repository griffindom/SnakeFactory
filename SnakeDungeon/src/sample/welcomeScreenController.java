package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class WelcomeScreenController {

    @FXML
    private Button startButton;

    private Scene scene;
    private int height;
    private int width;

    public WelcomeScreenController() {}

    public WelcomeScreenController(int width, int height)  throws IOException {
        this.width = width;
        this.height = height;
        this.scene = new Scene(FXMLLoader.load(getClass().getResource("WelcomeScreen.fxml")));
        this.startButton = (Button) scene.lookup("#startButton");
    }

    public Scene getScene() throws Exception { return this.scene; }

    public Button getStartButton() { return this.startButton; }
}
