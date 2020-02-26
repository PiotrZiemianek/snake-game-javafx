package pl.sda.snake.javafxui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import pl.sda.snake.Direction;
import pl.sda.snake.Point;
import pl.sda.snake.Snake;
import pl.sda.snake.SnakeGame;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller {
    public static final int POINT_SIZE = 20;
    @FXML
    private Canvas canvas;

    @FXML
    private Button retryButton;

    @FXML
    private Label scoreLabel;

    private GraphicsContext graphicsContext2D;
    private SnakeGame snakeGame;

    public void initialize() {
        graphicsContext2D = canvas.getGraphicsContext2D();

        int xBound = (int) canvas.getWidth() / POINT_SIZE;
        int yBound = (int) canvas.getHeight() / POINT_SIZE;

        newSnakeGame(xBound, yBound);

        retryButton.setOnAction(event -> {
            snakeGame.gameOn = false;
            newSnakeGame(xBound, yBound);
            startSnakeGame();

        });

        retryButton.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case W:
                case UP:
                    snakeGame.setSnakeDirection(Direction.UP);
                    break;
                case S:
                case DOWN:
                    snakeGame.setSnakeDirection(Direction.DOWN);
                    break;
                case A:
                case LEFT:
                    snakeGame.setSnakeDirection(Direction.LEFT);
                    break;
                case D:
                case RIGHT:
                    snakeGame.setSnakeDirection(Direction.RIGHT);
                    break;
                default:
                    break;

            }
        });

        startSnakeGame();
    }

    private void newSnakeGame(int xBound, int yBound) {
        snakeGame = new SnakeGame(xBound, yBound) {

            @Override
            public void draw() {

                graphicsContext2D.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                drawBackground();
                Color headColor = Color.GRAY;
                Color bodyColor = Color.DARKGRAY;
                Color appleColor = Color.RED;

                Snake snake = this.getSnake();
                drawPoint(this.getApple(), appleColor);
                drawPoint(snake.getHead(), headColor);
                for (Point point : snake.getBody()) {
                    drawPoint(point, bodyColor);
                }
                Platform.runLater(() -> scoreLabel.setText("Score: " + getScore()));

            }
        };
    }

    private void startSnakeGame() {
        if (!snakeGame.gameOn) {
            ExecutorService executorService = Executors.newSingleThreadExecutor(r -> {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                return thread;
            });
            executorService.submit(() -> snakeGame.start());
            executorService.shutdown();
        }
    }

    private void drawPoint(Point point, Color color) {
        graphicsContext2D.setFill(color);
        graphicsContext2D.fillRect(point.getX() * POINT_SIZE, point.getY() * POINT_SIZE, POINT_SIZE, POINT_SIZE);

    }

    private void drawBackground() {
        graphicsContext2D.setFill(Color.DEEPSKYBLUE);
        graphicsContext2D.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

    }

}
