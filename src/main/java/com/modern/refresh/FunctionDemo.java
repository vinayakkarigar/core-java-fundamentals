package com.modern.refresh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static com.modern.refresh.Apple.Color.GREEN;
import static com.modern.refresh.Apple.Color.RED;

public class FunctionDemo {
    public static <T, R> List<R> map(List<T> list, Function<T,R> function) {
        List<R> result = new ArrayList<>();
        for (T t :
                list) {
            result.add(function.apply(t));
        }
        return result;
    }

    public static void main(String[] args) {
        final List<Apple> apples = Arrays.asList(new Apple(GREEN, 100),
                new Apple(GREEN, 200),
                new Apple(RED, 300),
                new Apple(RED, 400),
                new Apple(GREEN, 110),
                new Apple(GREEN, 220),
                new Apple(RED, 330),
                new Apple(RED, 440),
                new Apple(RED, 550));
        map(apples, apple -> apple.getWeight()).forEach(System.out::println);
    }
}
