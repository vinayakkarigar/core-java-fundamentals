package com.modernjavainaction.chp6;

import com.vin.lambda.Dish;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class GroupingDemo {
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

        final HashMap<String, List<String>> dishTags = new HashMap<>();
        dishTags.put("pork", List.of("greasy", "salty"));
        dishTags.put("mutton", List.of("salty", "roasted"));
        dishTags.put("fruit", List.of("fresh", "natural"));
        dishTags.put("salad", List.of("light", "fresh", "natural"));
        dishTags.put("salmon", List.of("delicious", "fresh"));
        dishTags.put("french fries", List.of("greasy", "fried"));
        dishTags.put("prawns", List.of("tasty", "roasted"));
        dishTags.put("chicken", List.of("fried", "crispy"));
//        group dish by type

        final Map<Dish.Type, List<Dish>> collect = specialMenu.stream().collect(groupingBy(Dish::getType));
//        System.out.println(collect);

//        classifying with something more complex
        final Map<CaloricLevel, List<Dish>> collect1 = specialMenu.stream().collect(groupingBy(dish -> {
            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
            else return CaloricLevel.FAT;
        }));
        System.out.println(collect1);

//        grouping with filtering caloric dishes grouped by type.
        final Map<Dish.Type, List<Dish>> caloricDishesByType = specialMenu.stream().collect(groupingBy(
                Dish::getType, filtering(dish -> dish.getCalories() > 800, toList())
        ));
        System.out.println(caloricDishesByType);

//        grouping with transformation
        System.out.println("grouping with transformation");
        final Map<Dish.Type, List<String>> dishNamesByType = specialMenu.stream().collect(groupingBy(Dish::getType,
                mapping(Dish::getName, toList())
        ));
        System.out.println(dishNamesByType);

//    usage of flatmapping technique with grouping
        System.out.println("usage of flatmapping technique with grouping ");
        final Map<Dish.Type, Set<String>> dishNameByType = specialMenu.stream().collect(groupingBy(Dish::getType,
                flatMapping(dish ->
                                dishTags.get(dish.getName()).stream()
                        , toSet())
        ));
        System.out.println(dishNameByType);
    }



    public enum CaloricLevel {
        DIET, NORMAL, FAT
    }


}
