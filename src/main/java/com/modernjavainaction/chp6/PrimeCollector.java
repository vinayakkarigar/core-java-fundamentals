package com.modernjavainaction.chp6;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.*;

public class PrimeCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> {
            final HashMap<Boolean, List<Integer>> booleanListHashMap = new HashMap<>();
            booleanListHashMap.put(true, new ArrayList<>());
            booleanListHashMap.put(false, new ArrayList<>());
            return booleanListHashMap;
        };
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean, List<Integer>> m, Integer i) -> {
            final List<Integer> primes = m.get(true);
            if (isPrime(primes, i)) {
                primes.add(i);
            }
            else {
                m.get(false).add(i);
            }
        };
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (Map<Boolean, List<Integer>> m1, Map<Boolean, List<Integer>> m2) -> {
            m1.get(false).addAll(m2.get(false));
            m1.get(true).addAll(m2.get(true));
            return m1;
        };

    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.<Map<Boolean, List<Integer>>>identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH));
    }

    /**
     * check if the candidate is a prime.
     * @param primes
     * @param candidate
     * @return
     */
    public static boolean isPrime(List<Integer> primes, int candidate) {
        final int candidateRoot = (int) Math.sqrt((double) candidate);

        return primes.stream().takeWhile(i -> i <= candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }
}
