package com.vin.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatteningMapDemo {
    public static void main(String[] args) {
        final List<String> hello = List.of("Hello", "World");

        System.out.println(hello.stream()
                .map(s -> s.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList()));
        final List<Integer> integers = List.of(1, 2, 3, 4, 5);
        final List<Integer> collect = integers.stream().map(integer -> integer * integer).collect(Collectors.toList());
        System.out.println(collect);

        List<Integer> number1 = List.of(1, 2, 3);
        List<Integer> number2 = List.of(3, 4);

        final List<int[]> collect1 = number1.stream().flatMap(i ->
                number2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j})).
                collect(Collectors.toList());
        collect1.forEach(i -> System.out.println(Arrays.toString(i)));


    }
}
