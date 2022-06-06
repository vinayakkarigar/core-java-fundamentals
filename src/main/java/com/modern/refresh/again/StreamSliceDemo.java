package com.modern.refresh.again;

import com.modern.refresh.Dish;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.*;

public class StreamSliceDemo {
    /**
     * Demo the stream slice examples
     *
     * @param args
     */
    public static void main(String[] args) {
        //slicing with the predicate
        List<Dish> dishes = Dish.getSortedDishes();
//        System.out.println(dishes);
        List<Dish> dishStream = dishes.stream().takeWhile(d -> d.getCalories() < 320).collect(toList());
        dishStream.forEach(System.out::println);

        System.out.println("Drop all the dishes having calorie less than 320");
        List<Dish> collect = dishes.stream().dropWhile(d -> d.getCalories() < 320).collect(toList());
        collect.forEach(System.out::println);

        System.out.println("Truncate the dishes to get the 3 elements");
        List<Dish> collect1 = dishes.stream().limit(3).collect(toList());
        collect1.forEach(System.out::println);

        System.out.println("Skip the first 3 elements");
        List<Dish> collect2 = dishes.stream().skip(3).collect(toList());
        collect2.forEach(System.out::println);

        System.out.println(">>>>> Filter the first 2 MEAT elements");
        List<Dish> collect3 = dishes.stream().filter(d -> Dish.Type.MEAT.equals(d.getType())).limit(2).collect(toList());
        collect3.forEach(System.out::println);

        System.out.println(">>>>> Apply a function to each element of a stream");
        List<String> dishNames = dishes.stream().map(Dish::getName).collect(toList());
        System.out.println(dishNames);

        System.out.println(">>>>> Return the list of the length of the strings");
        List<String> words = Arrays.asList("Modern", "Java", "In", "Action");
        List<Integer> wordLength = words.stream().map(String::length).collect(toList());
        System.out.println(wordLength);

        System.out.println(">>>>> Dish name lengths");
        List<Integer> dishNameLengths = dishes.stream().map(Dish::getName).map(String::length).collect(toList());
        System.out.println(dishNameLengths);

        System.out.println(">>>>> Flattening the streams");


    }

}
