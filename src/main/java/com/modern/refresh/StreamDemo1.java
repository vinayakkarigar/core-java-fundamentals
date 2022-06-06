package com.modern.refresh;

import java.util.List;

import static java.util.stream.Collectors.toList;


public class StreamDemo1 {
    public static void main(String[] args) {
        final List<Dish> dishes = Dish.getDishes();

        final List<String> threeHighCaloricDishnames = dishes.stream()
                .filter(dish -> {
                    System.out.println("filtering::"+dish.getName());
                    return  dish.getCalories() > 300;})
                .map(dish -> {
                    System.out.println("mapping:: "+dish.getName());
                    return dish.getName();
                })
                .limit(3)
                .collect(toList());
        threeHighCaloricDishnames.forEach(System.out::println);

        System.out.println(">>>>>> Vegetarian dishes");
        final List<Dish> vegDishes = dishes.stream().filter(Dish::isVegetarian).collect(toList());
        vegDishes.forEach(System.out::println);

        System.out.println(">>>>> Stream distinct method demo");
        final List<Integer> integers = List.of(1, 2, 3, 3, 4, 5, 4, 5, 2, 2, 2, 6);
        integers.stream()
                .filter(integer -> integer % 2 == 0)
                .distinct()
                .forEach(System.out::println);

    }
}
