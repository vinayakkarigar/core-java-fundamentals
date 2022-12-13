package com.modern.refresh.again;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StreamReduceDemo {
    public static void main(String[] args) {
        System.out.println("Summing the elements");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Integer sum = numbers.stream().reduce(0, Integer::sum);
        System.out.println("sum: "+sum);

        System.out.println("Product of the elements");
        Optional<Integer> pro = numbers.stream().reduce((a, b) -> a * b);
        pro.ifPresent(System.out::println);

        System.out.println("Maximum");
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        max.ifPresent(System.out::println);

        System.out.println("Minimum");
        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        min.ifPresent(System.out::println);
    }
}
