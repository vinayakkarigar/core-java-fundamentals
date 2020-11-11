package com.modern.refresh;

import java.awt.event.PaintEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.modern.refresh.Apple.*;
import static com.modern.refresh.Apple.Color.*;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class BehaviourParameterizationDemo {

    public static void prettyPrintApple(List<Apple> apples, AppleFormatter appleFormatter) {
        for (Apple apple :
                apples) {
            System.out.println(appleFormatter.accept(apple));
        }
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.accept(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        final List<Apple> apples = Arrays.asList(new Apple(GREEN, 100),
                new Apple(GREEN, 200),
                new Apple(RED, 300),
                new Apple(RED, 400),
                new Apple(GREEN, 110),
                new Apple(GREEN, 220),
                new Apple(RED, 330),
                new Apple(RED, 440),
                new Apple(RED, 550));

//        filter all the green apple
        final List<Apple> greenApples
                = apples.stream().filter(apple -> GREEN.equals(apple.getColor())).collect(toList());
        greenApples.stream().forEach(System.out::println);

//        filter all the red apples
        final List<Apple> redApples
                = apples.stream().filter(apple -> RED.equals(apple.getColor())).collect(toList());
        redApples.stream().forEach(System.out::println);

//        sorting applies according to their weight
        System.out.println("Sorting sorting");
        apples.stream().sorted(comparing(Apple::getWeight, Integer::compareTo)).forEach(System.out::println);

//        check the apple formatter logic
        System.out.println("Apple Formatter demo");
        prettyPrintApple(apples, new AppleWeightFormatter());
        System.out.println("Apple Simple Formatter demo");
        prettyPrintApple(apples, new AppleSimpleFormatter());

//        more generic way to filter the apples which can be applied to any list
        System.out.println("More generic way to filter the red apples");
        final List<Apple> redApples1 = filter(apples, apple -> RED.equals(apple.getColor()));
        redApples1.forEach(System.out::println);

        final List<Integer> integers = List.of(1, 2, 3, 4, 5, 6);
        final List<Integer> evenNumber = filter(integers, integer -> integer % 2 == 0);
        evenNumber.forEach(System.out::println);

//      Real world problems: sorting using Comparator
        System.out.println("Sorting apples according to their weight using Comparator");
        apples.sort((Apple o1, Apple o2) -> o1.getWeight() - o2.getWeight());
        apples.forEach(System.out::println);
    }

}
