package com.modern.refresh;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimeNumberCollector
        implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

    public static boolean isPrime(List<Integer> primes, Integer candidate) {
        final int sqrt = (int) Math.sqrt((double) candidate);
        return primes.stream()
                .takeWhile(i -> i <= sqrt)
                .noneMatch(i -> candidate % i == 0);
    }

    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean, List<Integer>>() {{
            put(true, new ArrayList<Integer>());
            put(false, new ArrayList<Integer>());
        }};
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean, List<Integer>> m, Integer i) -> {
            if (i != 1) {
                m.get(isPrime(m.get(true), i)).add(i);
            }
        };
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (m1, m2) -> {
            m1.get(true).addAll(m2.get(true));
            m1.get(false).addAll(m2.get(false));
            return m1;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(
                EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT)
        );
    }

    public static void main(String[] args) {
        long fastest = Long.MAX_VALUE;

        for (int i = 0; i <= 10; i++) {

            final long start = System.nanoTime();
            partitionPrime(1_000_000);
            final long duration = (System.nanoTime() - start) / 1_000_000;
            if(duration < fastest) fastest = duration;
        }
        System.out.println("Fastest time using partitionBy::"+fastest+"msec");

        fastest = Long.MAX_VALUE;
        for (int i = 0; i <= 10; i++) {

            final long start = System.nanoTime();
            partitionPrimeWithCustomCollector(1_000_000);
            final long duration = (System.nanoTime() - start) / 1_000_000;
            if(duration < fastest) fastest = duration;
        }
        System.out.println("Fastest time using custom collector::"+fastest+ "msec");


    }



    public static Map<Boolean, List<Integer>> partitionPrime(Integer integer) {
        return  IntStream.rangeClosed(2, integer)
                .boxed()
                .collect(Collectors.partitioningBy(StreamPartitionDemo::isPrime));

    }

    public static Map<Boolean, List<Integer>> partitionPrimeWithCustomCollector(Integer integer) {
        return IntStream.rangeClosed(2, integer)
                .boxed()
                .collect(new PrimeNumberCollector());
    }

}
