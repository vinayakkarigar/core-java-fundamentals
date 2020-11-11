package com.vin.lambda;

import java.util.List;
import java.util.SortedMap;

public class MatchDemo {
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

        // anymatch
        if (specialMenu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("Menu is somewhat vegetarian friendly");

        }

        //allmatch
        if (specialMenu.stream().allMatch(dish -> dish.getCalories() < 3000)) {
            System.out.println("It is healthy dish");
        }

        if (specialMenu.stream().noneMatch(dish -> dish.getCalories() > 3000)) {
            System.out.println("For sure this is healthy food.");
        }
    }
}
