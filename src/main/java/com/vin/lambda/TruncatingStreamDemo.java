package com.vin.lambda;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class TruncatingStreamDemo {
    static List<Dish> specialMenu = List.of(
            new Dish("pork", false, 120, Dish.Type.MEAT),
            new Dish("mutton", false, 150, Dish.Type.MEAT),
            new Dish("fruit", true, 200, Dish.Type.OTHER),
            new Dish("salad", true, 400, Dish.Type.OTHER),
            new Dish("prawns", false, 800, Dish.Type.FISH),
            new Dish("salmon", false, 1200, Dish.Type.FISH),
            new Dish("french fries", true, 1600, Dish.Type.OTHER),
            new Dish("chicken", false, 2000, Dish.Type.MEAT)
    );

    public static void main(String[] args) {
        specialMenu.stream().filter(dish -> dish.getCalories() > 300)
                .limit(3).forEach(System.out::println);
        System.out.println("STEP 1");
        System.out.println(specialMenu.stream().filter(dish -> dish.getCalories() > 300)
                .skip(1)
                .collect(toList()));
    }
}
