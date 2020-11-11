package com.modernjavainaction.chp6;

import com.vin.lambda.Dish;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.modernjavainaction.chp6.GroupingDemo.CaloricLevel.*;
import static java.util.stream.Collectors.*;

public class MultilevelGroupingDemo {
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

        final Map<Dish.Type, Map<GroupingDemo.CaloricLevel, List<Dish>>> dishesByTypeCaloric
                = specialMenu.stream().collect(groupingBy(Dish::getType,
                groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return DIET;
                    if (dish.getCalories() <= 700) return NORMAL;
                    else return FAT;
                })));
        System.out.println(dishesByTypeCaloric);

    }
}
