package com.vin.lambda;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class NumericStreamDemo {
    public static void main(String[] args) {
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

        //get the sum of all the calories.
        final IntStream intStream = specialMenu.stream().mapToInt(Dish::getCalories);
        intStream.boxed();

        //get the maximum of calories
        specialMenu = List.of();
        final OptionalInt max = specialMenu.stream()
                .mapToInt(Dish::getCalories)
                .max();

        max.ifPresentOrElse(System.out::println, ()->System.out.println("its empty"));





    }

}
