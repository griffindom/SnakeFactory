package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    private Scene scene;
    private int height;
    private int width;
    private String roomType;

    public RoomController() {

    }

    public RoomController(int width, int height) throws IOException {
        this.width = width;
        this.height = height;
        this.scene = new Scene(FXMLLoader.load(getClass().getResource(this.randomScene())));
        this.enterGold = (Text) scene.lookup("#enterGold");
        this.goBack = (Button) scene.lookup("#goBack");
        this.initDoors();
    }

    public RoomController(int width, int height, String room) throws IOException {
        this.width = width;
        this.height = height;
        this.roomType = room;
        this.scene = new Scene(FXMLLoader.load(getClass().getResource(room)));
        this.enterGold = (Text) scene.lookup("#enterGold");
        this.goBack = (Button) scene.lookup("#goBack");
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

    public String randomScene() {
        String[] rooms = {"1door.fxml", "2door.fxml", "3door.fxml", "InitialGameScreen.fxml"};
        Random rand = new Random();
        int room = rand.nextInt(4);
        this.roomType = rooms[room];
        return rooms[room];
    }

    public void initDoors() {
        switch (this.roomType) {
        case "1door.fxml":
            door1 = (Button) scene.lookup("#door1");
            door2 = null;
            door3 = null;
            door4 = null;
            break;
        case "2door.fxml":
            door1 = (Button) scene.lookup("#door1");
            door2 = (Button) scene.lookup("#door2");
            door3 = null;
            door4 = null;
            break;
        case "3door.fxml":
            door1 = (Button) scene.lookup("#door1");
            door2 = (Button) scene.lookup("#door2");
            door3 = (Button) scene.lookup("#door3");
            door4 = null;
            break;
        case "InitialGameScreen.fxml":
            door1 = (Button) scene.lookup("#door1");
            door2 = (Button) scene.lookup("#door2");
            door3 = (Button) scene.lookup("#door3");
            door4 = (Button) scene.lookup("#door4");
            break;
        default:
            break;
        }
    }
}
