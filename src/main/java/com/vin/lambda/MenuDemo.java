package com.vin.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class MenuDemo {
    public static void main(String[] args) {
        List<Dish> menu = List.of(
                new Dish("pork", false, 900, Dish.Type.MEAT),
                new Dish("mutton", false, 900, Dish.Type.MEAT),
                new Dish("fruit", true, 900, Dish.Type.OTHER),
                new Dish("salad", true, 500, Dish.Type.OTHER),
                new Dish("prawns", false, 1000, Dish.Type.FISH),
                new Dish("salmon", false, 1200, Dish.Type.FISH),
                new Dish("french fries", true, 300, Dish.Type.OTHER),
                new Dish("chicken", false, 600, Dish.Type.MEAT)
        );
//        menu.stream().forEach(System.out::println);

//        three high calorie dish names
        final List<String> threeHighCalorieDishnames = menu
                .stream().filter(dish -> {
                    System.out.println("filtering:"+dish.getName());
                    return dish.getCalories() > 700;})
                .distinct()
                .map(dish1 -> {
                    System.out.println("mapping:"+dish1.getName());
                    return  dish1.getName();})
                .limit(3)
                .collect(toList());
        System.out.println(threeHighCalorieDishnames);
        System.out.println(menu.stream().filter(dish -> dish.getCalories() > 300)
                .distinct()
//                .limit(3)
                .count());

        menu.stream().filter(Dish::isVegetarian).collect(toList());

    }
}
