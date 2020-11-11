package com.vin.lambda;

import java.util.List;

public class FilteringDemo {
    public static void main(String[] args) {
        final List<Integer> integers = List.of(1, 2, 1, 3, 3, 2, 4);

        integers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()

                .forEach(System.out::println);
    }
}
