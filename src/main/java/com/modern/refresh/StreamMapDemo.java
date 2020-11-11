package com.modern.refresh;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamMapDemo {
    public static void main(String[] args) {
        final List<Dish> sortedDishes = Dish.getSortedDishes();
        System.out.println(">>>>> Stream Map Demo: get all the dish names");
        final List<String> collect = sortedDishes.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
        System.out.println(">>>>> Stream Map Demo: given a list of word return the list of length of each word");
        final List<String> words = List.of("World", "Hello", "How", "Vinayak", "hi");
        final List<Integer> collect1 = words.stream()
                .map(String::length)
                .collect(Collectors.toList());
        collect1.forEach(System.out::println);
        System.out.println(">>>>> Stream Map Demo: Get the number of words in each dishname");
        final List<Integer> collect2 = sortedDishes.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());
        collect2.forEach(System.out::println);

        System.out.println(">>>>> Stream Flat Map Demo: return a collection of unique characters in words ");
        final List<String> collect3 = words.stream()
                .map(s -> s.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        collect3.forEach(System.out::println);

        System.out.println(">>>> Stream Map Quiz 5.2 question1: given a list of numbers return the square of it");
        final List<Integer> numbers = List.of(2, 3, 4, 5, 6);
        final List<Integer> collect4 = numbers.stream()
                .map(integer -> integer * integer)
                .collect(Collectors.toList());
        collect4.forEach(System.out::println);

        System.out.println(">>>> Stream Map Quiz 5.2 question2: Given 2 list of numbers return the pair of numbers");
        final List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        final List<Integer> numbers2 = Arrays.asList(4, 5);

        final List<int[]> collect5 = numbers1.stream()
                .flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        collect5.forEach(ints -> System.out.println("("+ ints[0]+", "+ints[1]+" )"));

        System.out.println(">>>> Stream Map Quiz 5.2 question3: Given 2 list of numbers return the pair of numbers whose sum is divisible by 3");
        final List<int[]> collect6 = numbers1.stream()
                .flatMap(i -> numbers2.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        collect6.forEach(ints -> System.out.println("("+ ints[0]+", "+ints[1]+" )"));



    }
}
