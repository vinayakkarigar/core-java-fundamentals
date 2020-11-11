package com.vin.lambda;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PythagorianTriple {
    public static void main(String[] args) {
        /**
         * Generate pythagorian triples of the form (3,4,5)
         */
        final Stream<int[]> pythagorianTriples = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(i ->
                        IntStream.rangeClosed(i, 100)
                                .filter(b -> Math.sqrt(i * i + b * b) % 1 == 0)
                                .boxed()
                                .map(b -> new int[]{i, b, (int) Math.sqrt(i * i + b * b)})
                );
        pythagorianTriples.limit(5)
                .forEach(t -> System.out.println(t[0]+","+t[1]+","+t[2]));

    }
}
