package com.modernjavainaction.chp6;

import com.vin.lambda.Dish;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class ReduceAndSummarize {
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

        System.out.println(specialMenu.stream().collect(counting()));

        final Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);

        Optional<Dish> mostCalorieDish = specialMenu.stream()
                .collect(maxBy(dishCaloriesComparator));
        System.out.println("Most Calorie Dish:"+mostCalorieDish.get());

//        summing all the calories
        final Integer totalCalories = specialMenu.stream().collect(summingInt(Dish::getCalories));
        System.out.println("Total calories:: "+totalCalories);

//        averaging all the calories
        final Double avgCalories = specialMenu.stream().collect(averagingInt(Dish::getCalories));
        System.out.println("Average calories:: "+avgCalories);

//        summarizing the values
        final IntSummaryStatistics intSummaryStatistics
                = specialMenu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(intSummaryStatistics.toString());

//        joining
        final String shortMenu
                = specialMenu.stream().map(Dish::getName).collect(joining(" "));
        System.out.println("short menu::" + shortMenu);

//        more generic reducing function
        final Integer collect
                = specialMenu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
        System.out.println(collect);

//        joining using more general reducing operation
        final String collect1 = specialMenu.stream().collect(reducing("", Dish::getName, (s1, s2) -> s1 + " " + s2));
        System.out.println("Attempt1:: " + collect1);

        System.out.println(specialMenu.stream().map(Dish::getName).collect(reducing((s1, s2) -> s1 + " " + s2)).get());

    }
}
