package com.vin.lambda;

import java.util.List;
import java.util.stream.Stream;

public class TraverseStreamOnlyOnce {
    public static void main(String[] args) {
        final List<String> string = List.of("Modern", "Java", "in", "action");
        final Stream<String> stream = string.stream();
        stream.forEach(System.out::println);
        stream.forEach(System.out::println);
    }
}
