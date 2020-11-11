package com.modern.refresh;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamGroupingDemo {

    public enum CaloricLevel {
        DIET, NORMAL, FAT
    }

    public static void main(String[] args) {
        final List<Dish> dishes = Dish.getDishes();

        Map<String, List<String>> dishTags = new HashMap<>();
        dishTags.put("pork", List.of("greasy", "salty"));
        dishTags.put("beef", List.of("salty", "roasted"));
        dishTags.put("chicken", List.of("fried", "crispy"));
        dishTags.put("french fries", List.of("greasy", "fried"));
        dishTags.put("rice", List.of("light", "natural"));
        dishTags.put("seasonal fruit", List.of("fresh", "natural"));
        dishTags.put("pizza", List.of("tasty", "salty"));
        dishTags.put("prawns", List.of("tasty", "roasted"));
        dishTags.put("salmon", List.of("delicious", "fresh"));

        System.out.println("Stream Demo: Group dishes according to their type");
        final Map<Dish.Type, List<Dish>> collect = dishes.stream()
                .collect(groupingBy(Dish::getType));
        System.out.println(collect);

        System.out.println("Stream Grouping Demo: usage of more complex classification function");
        final Map<CaloricLevel, List<Dish>> collect1 = dishes.stream()
                .collect(groupingBy(
                        dish -> {
                            if (dish.getCalories() < 400) {
                                return CaloricLevel.DIET;
                            } else if (dish.getCalories() >= 400 && dish.getCalories() < 700) {
                                return CaloricLevel.NORMAL;
                            } else {
                                return CaloricLevel.FAT;
                            }
                        }
                ));
        System.out.println(collect1);

        System.out.println("Stream Grouping Demo: filter the grouped elements");
        final Map<Dish.Type, List<Dish>> collect3 = dishes.stream()
                .collect(groupingBy(Dish::getType, filtering(dish -> dish.getCalories() > 500, toList())));
        System.out.println(collect3);

        System.out.println("Stream Grouping Demo: group the dish names by type");
        final Map<Dish.Type, List<String>> collect4 = dishes.stream()
                .collect(groupingBy(Dish::getType, mapping(d -> d.getName(), toList())));
        System.out.println(collect4);

        System.out.println("Stream grouping Demo: sum of calories per dish type");
        final Map<Dish.Type, Optional<Integer>> collect2 = dishes.stream()
                .collect(groupingBy(
                        Dish::getType, mapping(Dish::getCalories, reducing(Integer::sum))
                ));
        System.out.println(collect2);

        System.out.println("Stream grouping Demo: extract tags for each group of dishes by type");
        final Map<Dish.Type, Set<String>> collect5 = dishes.stream()
                .collect(groupingBy(Dish::getType, flatMapping(dish -> {
                    return dishTags.get(dish.getName()).stream();
                }, toSet())));
        System.out.println(collect5);

        System.out.println("Stream grouping Demo: first group by type and then group by calorie");
        final Map<Dish.Type, Map<CaloricLevel, List<Dish>>> collect6 = dishes.stream()
                .collect(groupingBy(Dish::getType, groupingBy(d -> {
                    if (d.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (d.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                })));
        System.out.println(collect6);

        System.out.println("Stream grouping Demo: collecting data in subgroups, count the dishes in each group");
        final Map<Dish.Type, Long> collect7 = dishes.stream()
                .collect(groupingBy(Dish::getType, counting()));
        System.out.println(collect7);

        System.out.println("Stream grouping Demo: highest calorie dish classified by type");
        final Map<Dish.Type, Optional<Dish>> collect8 = dishes.stream()
                .collect(groupingBy(Dish::getType, maxBy(Comparator.comparingInt(Dish::getCalories))));
        System.out.println(collect8);

        System.out.println("Stream grouping Demo: lowest calorie dish classified by type");
        final Map<Dish.Type, Optional<Dish>> collect9 = dishes.stream()
                .collect(groupingBy(Dish::getType, minBy(Comparator.comparingInt(Dish::getCalories))));
        System.out.println(collect9);


    }
}
