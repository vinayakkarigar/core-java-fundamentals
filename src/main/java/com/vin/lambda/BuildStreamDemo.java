package com.vin.lambda;

import java.util.stream.Stream;

public class BuildStreamDemo {
    public static void main(String[] args) {
        // Stream.of method
        final Stream<String> stringStream = Stream.of("Modern", "Java", "in", "Action")
                .map(String::toUpperCase);
        stringStream
                .forEach(System.out::println);
        final Stream<String> emptyStream = Stream.empty();


    }

}
