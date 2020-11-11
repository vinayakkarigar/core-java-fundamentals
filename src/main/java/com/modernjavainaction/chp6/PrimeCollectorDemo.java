package com.modernjavainaction.chp6;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class PrimeCollectorDemo {

    


    public static void main(String[] args) {


        long fastest = Integer.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            final long starttime = System.nanoTime();
            System.out.println(NaivePrimePartitioningDemo.getprimes(1_000_000));
            final long duration = System.nanoTime() - starttime;
            if (duration < fastest) {
                fastest = duration;
                System.out.println(duration);
            }
        }
            System.out.println("Shortest Time Taken:: "+(int)fastest/1000000);
    }

    public static Map<Boolean, List<Integer>> getPrimesWithCustomCollector(int i) {

        final Map<Boolean, List<Integer>> collect = IntStream.rangeClosed(2, i)
                .boxed()
                .collect(new PrimeCollector());
        return collect;

    }

}
