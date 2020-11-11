package com.modern.refresh;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void testCompareXThenCompareY() {
        final Point point1 = new Point(10, 15);
        final Point point2 = new Point(10, 20);

        final int result = Point.compareXThenCompareY.compare(point1, point2);
        assertTrue(result < 0);
    }

    @Test
    void testMoveRightBy() {
        Point point = new Point(5, 5);
        point = point.moveRightBy(5);
        assertEquals(10, point.getX());
        assertEquals(5, point.getY());
    }

    @Test
    void testMoveAllPointsRightBy() {
        final List<Point> points = Arrays.asList(new Point(5, 10), new Point(10, 10));
        final List<Point> expectedPoints = Arrays.asList(new Point(15, 10), new Point(20, 10));

        final List<Point> resultPoints = Point.moveAllPointsRightBy(points, 10);
        assertEquals(expectedPoints, resultPoints);

    }
}