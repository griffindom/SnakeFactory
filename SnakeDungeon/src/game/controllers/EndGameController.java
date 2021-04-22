package game.controllers;

import game.support.GameModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import java.time.Duration;

import java.io.IOException;

public class EndGameController {

    @FXML
    private Button restart;
    @FXML
    private Button quit;
    @FXML
    private Text congratsText;
    @FXML
    private Text messageText;
    @FXML
    private Text timeText;
    @FXML
    private Text killedText;
    @FXML
    private Text damageText;

    private Scene scene;

    public EndGameController() throws IOException {
        this.scene = new Scene(FXMLLoader.load(
                getClass().getResource("/game/screens/EndScreen.fxml")));
        this.restart = (Button) scene.lookup("#restart");
        this.quit = (Button) scene.lookup("#quit");
        this.congratsText = (Text) scene.lookup("#congratsText");
        this.messageText = (Text) scene.lookup("#messageText");
        this.timeText = (Text) scene.lookup("#timeText");
        this.damageText = (Text) scene.lookup("#damageText");
        this.killedText = (Text) scene.lookup("#killedText");
    }

    public void setCongratsText() {
        this.congratsText.setText("Failed!");
    }

    public void setMessageText() {
        this.messageText.setText("You have failed to escape the Snake Dungeon!");
    }

    public void setTimeText(GameModel game) {
        Duration duration = game.getTotalTime();
        int min = (int) (duration.getSeconds() / 60);
        int sec = (int) (duration.getSeconds() % 60);
        String message = "Total time: " + min + ":" + sec;
        this.timeText.setText(message);
    }

    public void setKilledText(GameModel game) {
        String message = "Totals kills: " + game.getKills();
        this.killedText.setText(message);
    }

    public void setDamageText(GameModel game) {
        String message = "Total attack: " + game.getAttackDealt();
        this.damageText.setText(message);
    }

    public Scene getScene() {
        return this.scene;
    }

    public Button getRestart() {
        return this.restart;
    }

    public Button getQuit() {
        return this.quit;
    }
}