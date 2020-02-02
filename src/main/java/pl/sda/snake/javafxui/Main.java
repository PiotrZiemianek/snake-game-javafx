package pl.sda.snake.javafxui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/canvas-example.fxml"));

    @Override
    public void start(Stage stage) throws Exception {
        Parent vievRoot = fxmlLoader.load();
        stage.setScene(new Scene(vievRoot));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
