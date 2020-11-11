package com.modernjavainaction.chp6;

import com.vin.lambda.Dish;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class MultilevelGenericDemo {
    public static void main(String[] args) {
        List<Dish> specialMenu = List.of(
                new Dish("pork", false, 120, Dish.Type.MEAT),
                new Dish("mutton", false, 150, Dish.Type.MEAT),
                new Dish("fruit", true, 200, Dish.Type.OTHER),
                new Dish("salad", true, 400, Dish.Type.OTHER),
                new Dish("prawns", false, 700, Dish.Type.FISH),
                new Dish("salmon", false, 600, Dish.Type.FISH),
                new Dish("french fries", true, 1600, Dish.Type.OTHER),
                new Dish("chicken", false, 2000, Dish.Type.MEAT)
        );

        //get the count of dishes by type
        final Map<Dish.Type, Long> dishCountByType = specialMenu.stream().collect(groupingBy(Dish::getType,
                counting()));
        System.out.println(dishCountByType);

        //highest calorie dish grouped by type
        System.out.println("highest calorie dish grouped by type");
        final Map<Dish.Type, Optional<Dish>> mostCaloricByType = specialMenu.stream()
                .collect(groupingBy(Dish::getType,
                        maxBy(comparingInt(Dish::getCalories))));
        System.out.println(mostCaloricByType);

    }
}
