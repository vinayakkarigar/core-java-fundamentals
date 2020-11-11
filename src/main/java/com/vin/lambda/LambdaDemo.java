package com.vin.lambda;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.vin.lambda.Apple.Color.*;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class LambdaDemo {
    public static void main(String[] args) {
        List<Apple> inventory = List.of(
                new Apple(10, GREEN),
                new Apple(30, RED),
                new Apple(100, RED),
                new Apple(80, GREEN),
                new Apple(70, GREEN),
                new Apple(50, RED),
                new Apple(40, RED),
                new Apple(90, GREEN),
                new Apple(110, GREEN),
                new Apple(150, RED)
        );

//        farmer tells you to filter all green apples
//        final List<Apple> greenApples =
//                inventory.stream().filter((apple -> GREEN.equals(apple.getColor()))).collect(toList());
//        greenApples.forEach(System.out::println);

        final List<Apple> greenApples =
                inventory.stream().filter(apple -> GREEN.equals(apple.getColor())).collect(toList());
        greenApples.forEach(System.out::println);


//        // sorting of apples
//        final Stream<Apple> sortedApples = inventory.stream().sorted(comparing(Apple::getWeight));
//        sortedApples.forEach(System.out::println);

    }

}
