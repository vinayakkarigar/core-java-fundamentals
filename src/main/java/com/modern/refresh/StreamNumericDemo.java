package com.modern.refresh;

import java.util.Comparator;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamNumericDemo {
    public static void main(String[] args) {
        final List<Dish> dishes = Dish.getDishes();
        System.out.println("\nGet the sum of all the calories in the dishes using specialized stream");
        final int sum = dishes.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println("Sum: "+sum);

        System.out.println("\nGet the maximum calories");
        final OptionalInt max = dishes.stream()
                .mapToInt(Dish::getCalories)
                .max();
        max.ifPresent(System.out::println);

        System.out.println("\nPythagorean Triplets");
        IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0 )
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a*a + b*b)}))
                .forEach(t -> System.out.println(t[0]+","+t[1]+","+t[2]));

        IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a*a + b*b)})
                        .filter(t -> t[2] % 1 == 0))
                .forEach(t -> System.out.println(t[0]+","+t[1]+","+t[2]));

        System.out.println("Stream Demo: Stream of method");
        final Stream<String> hello = Stream.of("Hello", "hi", "How", "what", "when");
        hello.map(String::toUpperCase)
                .forEach(System.out::println);
        System.out.println("Stream Demo: Empty stream");
        Stream.empty();
}}
