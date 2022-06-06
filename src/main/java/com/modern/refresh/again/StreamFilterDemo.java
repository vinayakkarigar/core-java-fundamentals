package com.modern.refresh.again;

import com.modern.refresh.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class StreamFilterDemo {

    public static void main(String[] args) {
//        Filter all the high calorie dishes
        Dish.getDishes().stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(toList())
                .forEach(System.out::println);

//        Vegetarian Dishes
        System.out.println(">>>>> Vegetarian Dishes");
        List<Dish> vegDishes = Dish.getDishes().stream()
                .filter(Dish::isVegetarian)
                .collect(toList());
        vegDishes.forEach(System.out::println);

        System.out.println(">>>>> Stream Distinct Demo");
        List<Integer> integers = List.of(1, 1, 2, 4, 5, 2, 6, 7, 6);
        List<Integer> distinctIntegers = integers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .collect(toList());
        System.out.println(distinctIntegers);

        System.out.println(">>>> Stream square of the integers");
        List<Integer> integers1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares = integers1.stream()
                .map(i -> i * i)
                .collect(toList());
        System.out.println(squares);

        System.out.println(">>>>> Get all the pairs of number");
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        List<int[]> pairs = numbers1.stream()
                .flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
                .collect(toList());
        pairs.forEach(i -> System.out.println("["+ i[0] + " "+i[1]+"]"));
    }
}
