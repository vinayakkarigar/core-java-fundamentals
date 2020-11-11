package com.modern.refresh;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Point {
    private int x;
    private int y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public static List<Point> moveAllPointsRightBy(List<Point> points, int x) {
        return  points.stream()
                .map(p -> new Point(p.getX() + x, p.getY()))
                .collect(toList());
    }

    public static Comparator<Point> compareXThenCompareY
            = Comparator.comparing(Point::getX).thenComparing(Point::getY);



    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point moveRightBy(int x) {
        return new Point(this.x + x, y);
    }
}
