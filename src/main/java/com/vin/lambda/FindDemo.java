package com.vin.lambda;

import java.util.List;
import java.util.Optional;

public class FindDemo {
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

        //find any vegetarian dish
        final Optional<Dish> optDish = specialMenu.stream()
                .filter(Dish::isVegetarian)
                .findAny();
        optDish.ifPresent(System.out::println);

        final Optional<Dish> any1 = specialMenu.stream().filter(dish -> dish.getCalories() > 3000)
                .findAny();
        if (any1.isPresent()) {
            System.out.println("Present");
        }
        if (any1.isEmpty()) {
            System.out.println("Empty");
        }
        any1.ifPresentOrElse(System.out::println,()-> System.out.println("I am not present"));
        System.out.println(any1.orElse(new Dish("empty dish", false, 0, Dish.Type.OTHER)));
        System.out.println(any1.get());
    }
}
