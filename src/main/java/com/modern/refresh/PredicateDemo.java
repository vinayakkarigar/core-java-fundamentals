package com.modern.refresh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateDemo {

    //    its make use of the Predicate functional interface defined in java.util.funtions package
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        final List<String> strings = Arrays.asList("Hello", "HI", "", "", "For", "Each", "?");
        final List<String> nonEmptyStrings = filter(strings, s -> !s.isEmpty());
        nonEmptyStrings.forEach(System.out::println);
    }


}
