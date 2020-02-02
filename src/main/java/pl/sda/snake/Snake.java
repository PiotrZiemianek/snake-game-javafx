package pl.sda.snake;

import java.util.LinkedList;
import java.util.List;

public class Snake {
    private Point head;
    private List<Point> body;
    private Direction direction;

    public Snake(Point head, List<Point> body, Direction direction) {
        this.head = head;
        if (!head.getNeighbors().contains(body.get(0))) {
            throw new IllegalArgumentException("Wrong snake shape!");
        }
        for (int i = 0; i < body.size() - 1; i++) {

            if (!body.get(i).getNeighbors().contains(body.get(i + 1))) {
                throw new IllegalArgumentException("Wrong snake shape!");
            }
        }
        this.body = new LinkedList<>(body);
        if (direction == null) {
            throw new IllegalArgumentException("Wrong direction!");
        }
        this.direction = direction;
    }

    public void move() throws IllegalMoveException {
        expand();
        cutTail();
    }

    void expand() throws IllegalMoveException {
        body.add(0, head);
        switch (direction) {

            case UP:
                head = new Point(head.getX(), head.getY() - 1);
                break;
            case DOWN:
                head = new Point(head.getX(), head.getY() + 1);
                break;
            case LEFT:
                head = new Point(head.getX() - 1, head.getY());
                break;
            case RIGHT:
                head = new Point(head.getX() + 1, head.getY());
                break;
        }
        if (body.contains(head)) {
            throw new IllegalMoveException();
        }
    }

    public void cutTail() {
        body.remove(body.size() - 1);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Point getHead() {
        return head;
    }

    public List<Point> getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Snake{" +
                "head=" + head +
                ", body=" + body +
                '}';
    }

    public boolean contains(Point point) {
        return body.contains(point) || head.equals(point);
    }
}
