package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicReference;

public class Main extends Application {
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
            if (checkNameHelper(name, weaponSelected)) {
                gameModel = new GameModel(name, diff, weaponSelected.get(), startingGold);
                System.out.println(gameModel.toString());
                try {
                    goToInitialGameScreen();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        mainWindow.setScene(scene);
        mainWindow.show();
    }

    private void goToInitialGameScreen() throws Exception {
        InitialGameScreenController initialGameScreenController =
                new InitialGameScreenController(width, height);
        Scene scene = initialGameScreenController.getScene();

        Text gold = (Text) scene.lookup("#enterGold");
        gold.setText(gameModel.getTotalGold() + " Gold Coins");

        mainWindow.setScene(scene);
        mainWindow.show();
    }

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

    public static void main(String[] args) {
        launch(args);
    }
}
