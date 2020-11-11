package com.vin.lambda;

import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GeneratorStreamDemo {
    public static void main(String[] args) {
//        Stream.generate(() -> Math.round(Math.random())).limit(5).forEach(System.out::println);

        //Be aware supplier here is statefull !!! not good for parallelization
        final IntSupplier fib = new IntSupplier() {
            int previous = 0;
            int current = 1;

            @Override
            public int getAsInt() {
                int oldPrevious = previous;
                int nextvalue = previous + current;
                previous = current;
                current = nextvalue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }
}
