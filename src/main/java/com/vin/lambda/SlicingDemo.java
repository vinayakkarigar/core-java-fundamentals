package com.vin.lambda;

import java.util.List;

public class SlicingDemo {
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

        /**
         * TakeWhile and dropwhile
         */
        //filter dishes with values greated then 320
        specialMenu.stream().takeWhile(dish -> dish.getCalories() < 320)
                .forEach(System.out::println);
        System.out.println("HIHIHIHIHIHIHIHIHIHIHIHIIH");
        //filter dishes greated than 320
        specialMenu.stream().dropWhile(dish -> dish.getCalories() < 320)
                .forEach(System.out::println);
    }
}
