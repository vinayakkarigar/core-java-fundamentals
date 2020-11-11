package com.modern.refresh;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class StreamPartitionDemo {

    public static boolean isPrime(int i) {
        final int sqrt = (int) Math.sqrt(i);
        return IntStream.rangeClosed(2, sqrt).boxed()
                .noneMatch(j -> i % j == 0);
    }

    public static void main(String[] args) {
        final List<Dish> dishes = Dish.getDishes();
        System.out.println("Stream partition Demo: partition the menu into veg and non-veg");
        final Map<Boolean, List<Dish>> collect = dishes.stream()
                .collect(partitioningBy(Dish::isVegetarian));
        System.out.println(collect);

        System.out.println("Stream Partition Demo: multi-level partitioning");
        final Map<Boolean, Map<Dish.Type, List<Dish>>> collect1 = dishes.stream()
                .collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
        System.out.println(collect1);

        System.out.println("Stream Partition Demo: Most Caloric dish per partition");
        final Map<Boolean, Dish> collect2 = dishes.stream()
                .collect(partitioningBy(Dish::isVegetarian,
                        collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println(collect2);

        System.out.println("Stream Partition Demo: partition numbers into prime and non-primes");
        final Map<Boolean, List<Integer>> collect3 = IntStream.rangeClosed(1, 100).boxed()
                .collect(partitioningBy(StreamPartitionDemo::isPrime));
        System.out.println(collect3);

        System.out.println("Usage of toCollection method");
        final ArrayList<Dish> collect4 = dishes.stream()
                .collect(toCollection(ArrayList::new));
        System.out.println(collect4);

        System.out.println("Count number of dishes");
        final Long collect5 = dishes.stream()
                .collect(counting());
        System.out.println(collect5);

        System.out.println("Total calories in the menu");
        final Integer collect6 = dishes.stream()
                .collect(summingInt(Dish::getCalories));
        System.out.println(collect6);

        System.out.println("Average number of calories in the menu");
        final Double collect7 = dishes.stream()
                .collect(averagingInt(Dish::getCalories));
        System.out.println(collect7);

        System.out.println("Summerizing statistics of the dishes");
        final IntSummaryStatistics collect8 = dishes.stream()
                .collect(summarizingInt(Dish::getCalories));
        System.out.println(collect8);

        System.out.println("Join all the dish names separated by commma");
        final String collect9 = dishes.stream()
                .map(Dish::getName)
                .collect(joining(","));
        System.out.println(collect9);

        System.out.println("Highest Caloric dish");
        final Optional<Dish> collect10 = dishes.stream()
                .collect(maxBy(Comparator.comparingInt(Dish::getCalories)));
        System.out.println(collect10);

        System.out.println("Lowest Caloric dish");
        final Optional<Dish> collect11 = dishes.stream()
                .collect(minBy(Comparator.comparingInt(Dish::getCalories)));
        System.out.println(collect11);

        System.out.println("Number of calories using the reducing");
        final Integer collect12 = dishes.stream()
                .collect(reducing(0, Dish::getCalories, Integer::sum));
        System.out.println(collect12);

        System.out.println("collectingAndThen example");
        final Integer collect14 = dishes.stream()
                .collect(collectingAndThen(toList(), List::size));
        System.out.println(collect14);

        System.out.println("Group dishes by type");
        final Map<Dish.Type, List<Dish>> collect13 = dishes.stream()
                .collect(groupingBy(Dish::getType));
        System.out.println(collect13);

        System.out.println("partition into veg and non-veg");
        final Map<Boolean, List<Dish>> collect15 = dishes.stream()
                .collect(partitioningBy(Dish::isVegetarian));
        System.out.println(collect15);

    }


}
