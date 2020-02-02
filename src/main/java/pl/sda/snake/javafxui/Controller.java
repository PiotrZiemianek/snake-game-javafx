package pl.sda.snake.javafxui;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import pl.sda.snake.SnakeGame;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller {
    public static final int POINT_SIZE = 10;
    @FXML
    private Canvas canvas;

    @FXML
    private Button up;

    @FXML
    private Button left;

    @FXML
    private Button down;

    @FXML
    private Button right;

    public void initialize() {

//        SnakeGame
    }

    private void drawRectangleExample() {
        GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();
        graphicsContext2D.setFill(Color.DARKCYAN);
        graphicsContext2D.fillRect(20, 20, 100, 70);

    }

    private void animationExample() {
        ExecutorService ec = Executors.newSingleThreadExecutor();
        ec.submit(() -> {
            GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();
            graphicsContext2D.setFill(Color.DARKRED);
            for (int i = 0; i < 20; i++) {
                graphicsContext2D.clearRect(0, 0, canvas.getHeight(), canvas.getWidth());
                graphicsContext2D.fillRect(i * 10, i, 20, 20);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ec.shutdown();
        });
    }
}
