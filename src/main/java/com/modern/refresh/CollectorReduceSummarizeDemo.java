package com.modern.refresh;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class CollectorReduceSummarizeDemo {

    public static void main(String[] args) {
        final List<Dish> dishes = Dish.getDishes();

        System.out.println("Collectors Demo: Count the number of dishes");
        final Long collect = dishes.stream()
                .collect(counting());
        System.out.println("Count:" + collect);

        System.out.println("Collectors Demo: usage of max by collector");
        final Optional<Dish> collect1 = dishes.stream()
                .collect(maxBy(Comparator.comparingInt(Dish::getCalories)));
        collect1.ifPresent(System.out::println);

        System.out.println("Collectors Demo: usage of min by collector");
        final Optional<Dish> collect2 = dishes.stream()
                .collect(minBy(Comparator.comparingInt(Dish::getCalories)));
        collect2.ifPresent(System.out::println);

        System.out.println("Collectors Demo: sum all the calories");
        final Integer collect3 = dishes.stream()
                .collect(summingInt(Dish::getCalories));
        System.out.println(collect3);

        System.out.println("Collectors Demo: averaging all the calories");
        final Double collect4 = dishes.stream()
                .collect(averagingInt(Dish::getCalories));
        System.out.println(collect4);

        System.out.println("Collectors Demo: summarizing everything");
        final IntSummaryStatistics collect5 = dishes.stream()
                .collect(summarizingInt(Dish::getCalories));
        System.out.println(collect5);

        System.out.println("Collectors Demo: concatenate all the dish names");
        final String collect6 = dishes.stream()
                .map(Dish::getName)
                .collect(joining(" "));
        System.out.println(collect6);

        System.out.println("Reducing Demo: total calories using reducing");
        final Integer collect7 = dishes.stream()
                .collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
        System.out.println(collect7);

        System.out.println("Reduction Demo: most caloric dish using reducing");
        final Optional<Dish> collect8 = dishes.stream()
                .collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        System.out.println(collect8);

        System.out.println("Reduction Demo: total calories more streamlined");
        final Integer collect9 = dishes.stream()
                .collect(reducing(0, Dish::getCalories, Integer::sum));
        System.out.println(collect9);

        System.out.println("Reduction Demo: joining strings using reducing");
        final Optional<String> collect10 = dishes.stream()
                .map(Dish::getName)
                .collect(reducing((s1, s2) -> s1 + "," + s2));
        collect10.ifPresent(System.out::println);
    }

}
