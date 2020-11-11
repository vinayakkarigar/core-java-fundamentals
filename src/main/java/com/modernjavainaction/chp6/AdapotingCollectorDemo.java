package com.modernjavainaction.chp6;

import com.vin.lambda.Dish;

import java.util.*;
import java.util.stream.Collectors;

import static com.modernjavainaction.chp6.GroupingDemo.CaloricLevel.*;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class AdapotingCollectorDemo {
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

        final Map<Dish.Type, Dish> mostCaloricByType = specialMenu.stream()
                .collect(groupingBy(Dish::getType,
                        collectingAndThen(maxBy(comparingInt(Dish::getCalories)),
                                Optional::get)));
        System.out.println(mostCaloricByType);

//        using mapping along with collectors
        System.out.println("using mapping along with collectors");
        final Map<Dish.Type, HashSet<GroupingDemo.CaloricLevel>> collect = specialMenu.stream()
                .collect(groupingBy(Dish::getType,
                        mapping(dish -> {
                            if (dish.getCalories() <= 400) return DIET;
                            else if (dish.getCalories() <= 700) return NORMAL;
                            else return FAT;
                        }, toCollection(HashSet::new))));
        System.out.println(collect);

    }
}
