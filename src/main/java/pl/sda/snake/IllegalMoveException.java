package pl.sda.snake;

public class IllegalMoveException extends Exception {
    public IllegalMoveException(String message) {
        super(message);
    }

    public IllegalMoveException() {
    }
}
