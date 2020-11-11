package com.modern.refresh;

import java.util.List;

public class StreamFindAndMatchDemo {

    public static void main(String[] args) {
        final List<Dish> dishes = Dish.getDishes();

        System.out.println(">>>>> Stream AnyMatch Demo: check if the menu is vegetarian friendly");
        if (dishes.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("Menu is somewhat vegetarian friendly");
        }

        System.out.println(">>>>> Stream AllMatch Demo: check if all the dishes are below 1000 calories");
        if (dishes.stream().allMatch(dish -> dish.getCalories() < 1000)) {
            System.out.println("All dishes are below 1000 calories");
        }

        System.out.println(">>>>> Stream NoneMatch Demo: check if all the dishes are below 1000 calories");
        final boolean isHealthy = dishes.stream().noneMatch(dish -> dish.getCalories() >= 1000);
        if (isHealthy) {
            System.out.println("All dishes are healthy in the menu!!!");
        }
        System.out.println("Note: AnyMatch AllMatch, NoneMatch all use the short circuiting technique");

        System.out.println(">>>>> Stream find any Demo: find any vegetarian dish in the menu");
        dishes.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(dish -> System.out.println(dish.getName()));

        System.out.println(">>>>> Stream findFirst Demo: find the first element square that is divisible by 3");
        final List<Integer> someNumbers = List.of(1, 2, 3, 4, 5);
        someNumbers.stream()
                .map(i -> i * i)
                .filter(i -> i % 3 == 0)
                .findFirst()
                .ifPresent(System.out::println);

    }

}
