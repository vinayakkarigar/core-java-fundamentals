package com.vin.lambda;

import java.util.List;
import java.util.stream.Collectors;

public class MapDemo {
    public static void main(String[] args) {
        List<String> words = List.of("Modern", "Java", "In", "Action");
        final List<Integer> integers = words.stream().map(String::length).collect(Collectors.toList());
        System.out.println(integers);

        List<Dish> specialMenu = List.of(
                new Dish("pork", false, 120, Dish.Type.MEAT),
                new Dish("mutton", false, 150, Dish.Type.MEAT),
                new Dish("fruit", true, 200, Dish.Type.OTHER),
                new Dish("salad", true, 400, Dish.Type.OTHER),
                new Dish("prawns", false, 800, Dish.Type.FISH),
                new Dish("salmon", false, 1200, Dish.Type.FISH),
                new Dish("french fries", true, 1600, Dish.Type.OTHER),
                new Dish("chicken", false, 2000, Dish.Type.MEAT)
        );

        final List<Integer> dishLengths
                = specialMenu.stream().map(Dish::getName).map(String::length).collect(Collectors.toList());
        System.out.println(dishLengths);
    }
}
