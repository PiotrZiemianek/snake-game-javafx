package pl.sda.snake;

import java.util.ArrayList;
import java.util.List;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // W przypadku, gdy tworzymy niepoprawnego węża (kolejne punkty nie są sąsiadujące) wyrzuć wyjątek
    // typu IllegalArgumentException.
    public List<Point> getNeighbors() {
        List<Point> neighbors = new ArrayList<>();
        neighbors.add(new Point(this.getX(), this.getY() - 1));
        neighbors.add(new Point(this.getX(), this.getY() + 1));
        neighbors.add(new Point(this.getX() - 1, this.getY()));
        neighbors.add(new Point(this.getX() + 1, this.getY()));
        return neighbors;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (!(obj instanceof Point)) {
//            return false;
//        }
//        Point point = (Point) obj;
//
//        return this.x == point.x && this.y == point.y;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
