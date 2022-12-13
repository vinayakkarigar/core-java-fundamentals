package com.modern.refresh.again;

import com.modern.refresh.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.SortedMap;

public class StreamFindMatchDemo {
    public static void main(String[] args) {
        System.out.println(">>> Check to see if a predicate matches atleast one element");
        List<Dish> menu = Dish.getDishes();
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The Menu is vegetarian friendly");

        }
        System.out.println(">>>> Check to see if a predicate matches all the elements");
        boolean isHealthy = menu.stream().allMatch(d -> d.getCalories() < 1000);
        System.out.println("Menu is healthy ? " + isHealthy);

        System.out.println(">>>> None Matches the elements of the stream");
        boolean healthy = menu.stream().noneMatch(dish -> dish.getCalories() >= 1000);
        System.out.println(healthy);

        System.out.println(">>>> Finding an element that matches the predicate");
        Optional<Dish> dis = menu.stream().filter(Dish::isVegetarian).findAny();
        dis.ifPresent(System.out::println);

        System.out.println(">>>> Finding the first element");
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree = someNumbers.stream().map(n -> n * n).filter(n -> n % 3 == 0).findFirst();
        firstSquareDivisibleByThree.ifPresent(System.out::println);
    }
}
