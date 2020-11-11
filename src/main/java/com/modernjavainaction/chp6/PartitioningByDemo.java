package com.modernjavainaction.chp6;

import com.vin.lambda.Dish;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class PartitioningByDemo {
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

//        Partitioning dishes into veg and non-veg
        System.out.println("Partitioning dishes into veg and non-veg");
        final Map<Boolean, List<Dish>> partitionedMenu
                = specialMenu.stream().collect(partitioningBy(Dish::isVegetarian));
        final List<Dish> vegDishes = partitionedMenu.get(true);
        System.out.println(vegDishes);

        final Map<Boolean, Map<Dish.Type, List<Dish>>> vegDishesByType
                = specialMenu.stream().collect(partitioningBy(Dish::isVegetarian,
                groupingBy(Dish::getType)));
        System.out.println(vegDishesByType);

//        most caloric partitioned by veg and non-veg
        System.out.println("//        most caloric partitioned by veg and non-veg");
        final Map<Boolean, Dish> mostCaloricPartitionedByVeg
                = specialMenu.stream().collect(partitioningBy(Dish::isVegetarian,
                collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)
                ));
        System.out.println(mostCaloricPartitionedByVeg);

//        multi level partitioning example
        System.out.println("multi level partitioning example");
        final Map<Boolean, Map<Boolean, List<Dish>>> collect = specialMenu.stream().collect(partitioningBy(Dish::isVegetarian,
                partitioningBy(d -> d.getCalories() > 500)));
        System.out.println(collect);

//        multi level partitioning example 1
        System.out.println("multi level partitioning example 1");
        final Map<Boolean, Long> collect1 = specialMenu.stream().collect(partitioningBy(Dish::isVegetarian,
                counting()));
        System.out.println(collect1);

        final ArrayList<Dish> collect2 = specialMenu.stream().collect(toCollection(ArrayList::new));
        System.out.println(collect2);
    }



}
