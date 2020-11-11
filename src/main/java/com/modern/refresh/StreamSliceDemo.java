package com.modern.refresh;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class StreamSliceDemo {

    public static void main(String[] args) {
        final List<Dish> sortedDishes = Dish.getSortedDishes();
        System.out.println(">>>>>> Stream takewhile method demo: filter all dishes having calories less than 320");
        sortedDishes.stream()
                .takeWhile(dish -> dish.getCalories() < 320)
                .forEach(dish -> System.out.println(dish.getName()));

        System.out.println(">>>>>> Stream dropwhile method demo: filter all dishes having calories more than 320");
        sortedDishes.stream()
                .dropWhile(dish -> dish.getCalories() < 320)
                .forEach(dish -> System.out.println(dish.getName()));

        System.out.println(">>>>>> Stream truncating demo: Truncate the stream to 3 dishes having 300 or more  calories");
        sortedDishes.stream()
                .filter(dish -> dish.getCalories() > 300)
                .limit(3)
                .forEach(dish -> System.out.println(dish.getName()));
        System.out.println(">>>>>> Stream skip demo: skip the first 2 dishes having 300 or more  calories");
        final List<Dish> collect = sortedDishes.stream()
                .filter(dish -> dish.getCalories() > 300)
                .skip(2)
                .collect(toList());
        collect.forEach(dish -> System.out.println(dish.getName()));
        System.out.println(">>>>>> Stream Quiz 5.1 Filtering the first 2 meat dishes");
        sortedDishes.stream()
                .filter(dish -> dish.getType() == Dish.Type.MEAT)
                .limit(2)
                .forEach(dish -> System.out.println(dish.getName()));

    }

}
