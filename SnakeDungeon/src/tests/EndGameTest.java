package tests;

import game.controllers.MainController;
import game.controllers.RoomController;
import game.support.GameModel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class EndGameTest extends ApplicationTest {

    private MainController controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new MainController();
        controller.start(primaryStage);
    }
}