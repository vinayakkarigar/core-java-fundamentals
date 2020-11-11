package com.modernjavainaction.chp6;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NaivePrimePartitioningDemo {


    //    utility method for identifying prime number
    public static boolean isPrime(int candidate) {
        final int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }

    public static void main(String[] args) {
//        split the first n natural numbers into prime and non prime
        System.out.println("split the first n natural numbers into prime and non prime");

        final Map<Boolean, List<Integer>> primePartitions = IntStream.range(0, 100)
                .boxed()
                .collect(Collectors.partitioningBy(c -> isPrime(c)));
        System.out.println(primePartitions);

    }

    public static Map<Boolean, List<Integer>> getprimes(int n) {
        return IntStream.range(2, n)
                .boxed()
                .collect(Collectors.partitioningBy(c -> isPrime(c)));
    }

}
