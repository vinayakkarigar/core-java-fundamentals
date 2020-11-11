package com.modern.refresh;

import java.util.Arrays;
import java.util.List;

public class DebugDemo {

    public static void main(String[] args) {
/*
        final List<Point> points = Arrays.asList(new Point(5, 5), null);
        points.stream()
                .map(Point::getX)
                .forEach(System.out::println);
*/
        final List<Integer> integers = List.of(1, 2, 3, 4, 5);
        integers.stream()
                .peek(i -> System.out.println("from stream"+i))
                .filter(i -> i < 4)
                .peek(i -> System.out.println("after filter"+i))
                .map(i -> i*2)
                .peek(i -> System.out.println("after map"+i))
                .limit(5)
                .peek(i -> System.out.println("after limit"+i))
                .forEach(System.out::println);

    }

}
