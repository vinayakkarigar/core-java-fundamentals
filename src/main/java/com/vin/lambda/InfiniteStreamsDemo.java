package com.vin.lambda;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class InfiniteStreamsDemo {

    public static void main(String[] args) {
        Stream.iterate(0, i -> i+1).limit(10).forEach(System.out::println);

        //generate fibonnaci pairs (0,1) (1,2) (2,3) ...
        Stream.iterate(new int[]{0,1}, a -> new int[]{a[1],a[0]+a[1]})
                .limit(10).forEach(t -> System.out.println("("+t[0]+" "+t[1]+")"));

        //just the fibonnaci numbers
        Stream.iterate(new int[]{0,1}, a -> new int[]{a[1],a[0]+a[1]})
                .limit(10).map(i -> i[0]).forEach(System.out::println);

        IntStream.iterate(0, n -> n < 100, n -> n+4).forEach(System.out::println);
    }
}
