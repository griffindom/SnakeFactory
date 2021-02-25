package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

public class InitialGameScreenController {

    private Scene scene;
    private int height;
    private int width;

    public InitialGameScreenController(){}

    public InitialGameScreenController(int width, int height) throws IOException {
        this.width = width;
        this.height = height;
        this.scene = new Scene(FXMLLoader.load(getClass().getResource("InitialGameScreen.fxml")));
    }

    public Scene getScene() throws Exception { return this.scene; }
}
