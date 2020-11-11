package com.modern.refresh;

import java.util.List;

public class StreamReductionDemo {
    public static void main(String[] args) {
        final List<Dish> dishes = Dish.getDishes();

        final List<Integer> numbers = List.of(4, 5, 6, 3);
        System.out.println("Stream Reduction Demo: Sum all the numbers using reduce with initial value:0");
        System.out.println("Sum:"+ numbers.stream()
                .reduce(0, (a, b) -> a + b));

        System.out.println("Stream Reduction Demo: multiply all the numbers using reduce with initial value:1");
        System.out.println("Product: "+numbers.stream()
                .reduce(1, (a, b) -> a * b));

        System.out.println("Stream Reduction Demo: sum all numbers making use of method reference shortcut");
        final Integer sum = numbers.stream()
                .reduce(0, Integer::sum);
        System.out.println("Sum :"+sum);

        System.out.println("Stream Reduction Demo: sum all numbers without the initial value");
        numbers.stream()
                .reduce(Integer::sum)
                .ifPresent(System.out::println);

        System.out.println("Stream Reduction Demo: Find the maximum number");
        numbers.stream()
                .reduce(Integer::max)
                .ifPresent(i -> System.out.println("Max:"+i));

        System.out.println("stream Reduction Demo: Find the minimum number");
        numbers.stream()
                .reduce(Integer::min)
                .ifPresent(i -> System.out.println("Min:"+i));

        System.out.println("Stream Reduction Quiz 5.3 Question 1: Count number of dishes in the menu");
        dishes.stream()
                .map(dish -> 1)
                .reduce(Integer::sum)
                .ifPresent(i -> System.out.println("Count of dishes:"+i));
    }
}
