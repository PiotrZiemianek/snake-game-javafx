package pl.sda.snake.consoleui;

import pl.sda.snake.Direction;
import pl.sda.snake.Point;
import pl.sda.snake.Snake;
import pl.sda.snake.SnakeGame;

import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService ec = Executors.newSingleThreadExecutor(runnable -> {
            Thread thread = new Thread(runnable);
            thread.setDaemon(true);
            return thread;
        });
        Snake snake = new Snake(new Point(1, 0), Collections.singletonList(new Point(0, 0)), Direction.RIGHT);
        SnakeGame snakeGame = new SnakeGame(10, 10, snake) {
            @Override
            public void draw() {
                System.out.println("-------------------");
                System.out.println("Score: " + this.getScore());
                System.out.println(this); //klasa anonimowa nadpisująca metodę draw();
            }
        };
        ec.submit(() -> {
            Scanner sc = new Scanner(System.in);
            while (snakeGame.gameOn) {
                String input = sc.nextLine();
                switch (input) {
                    case "w":
                        snake.setDirection(Direction.UP);
                        break;
                    case "s":
                        snake.setDirection(Direction.DOWN);
                        break;
                    case "a":
                        snake.setDirection(Direction.LEFT);
                        break;
                    case "d":
                        snake.setDirection(Direction.RIGHT);
                        break;
                    default:
                        break;

                }
            }
        });
        snakeGame.start();
        ec.shutdown();

    }

}
