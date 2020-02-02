package pl.sda.snake;

import java.util.Collections;
import java.util.Random;

public abstract class SnakeGame {
    private int xBound;
    private int yBound;
    private Snake snake;
    private Point apple;
    private int score;
    public boolean gameOn = true;

    public SnakeGame(int xBound, int yBound, Snake snake) {
        if (xBound < 1 || yBound < 1) {
            throw new IllegalArgumentException("Incorrect bounds!");
        }
        if (snake == null) throw new IllegalArgumentException("Incorrect snake");
        this.xBound = xBound;
        this.yBound = yBound;
        this.snake = snake;
//        randomizeApple();
        apple = new Point(7, 1);
    }

    public SnakeGame(int xBound, int yBound) {
        this(xBound, yBound, new Snake(new Point(0, 0), Collections.emptyList(), Direction.RIGHT));
    }

    public void start() {
        gameOn = true;
        while (isSnakeInBound()) {
//            System.out.println("-------------------");
//            System.out.println("Score: " + score);
//            System.out.println(toString()); //zamiast tego metoda abstrakcyjna draw();
            draw();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                snake.expand();
            } catch (IllegalMoveException e) {
                System.out.println(toString());
                break;
            }
            if (snake.getHead().equals(apple)) {
                score++;
                randomizeApple();
            } else {
                snake.cutTail();
            }
            //todo Zakończ grę również w przypadku, gdy wąż zajmuje całe pole gry.
        }
        gameOn = false;
        System.out.println("End game");
    }

    public abstract void draw();

    private boolean isSnakeInBound() {
        Point head = snake.getHead();

        int headX = head.getX();
        int headY = head.getY();

        return headX >= 0 && headX <= xBound - 1 && headY >= 0 && headY <= yBound - 1;
    }


    private void randomizeApple() {
        Random random = new Random();
        do {
            int randX = random.nextInt(xBound);
            int randY = random.nextInt(yBound);
            apple = new Point(randX, randY);
        } while (snake.contains(apple));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < yBound; y++) {
            for (int x = 0; x < xBound; x++) {
                Point pixel = new Point(x, y);
                if (snake.getHead().equals(pixel)) {
                    sb.append(" H ");
                } else if (snake.getBody().contains(pixel)) {
                    sb.append(" B ");
                } else if (apple.equals(pixel)) {
                    sb.append(" A ");
                } else {
                    sb.append(" * ");
                }
            }
            sb.append("\n");

        }
        return sb.toString();
    }

    public int getScore() {
        return score;
    }
}
