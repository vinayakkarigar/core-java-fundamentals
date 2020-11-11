package com.vin.lambda;

import java.util.stream.IntStream;

public class NumericRangeDemo {
    public static void main(String[] args) {
        final IntStream range = IntStream.range(1, 101);
        System.out.println(range.boxed().reduce((a, b) -> a + b));

        final IntStream rangeClosed = IntStream.rangeClosed(1, 100);

    }

}
