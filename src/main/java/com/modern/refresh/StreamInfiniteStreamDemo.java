package com.modern.refresh;

import java.util.stream.Stream;

public class StreamInfiniteStreamDemo {

    public static void main(String[] args) {

        System.out.println("Infinite Stream Demo");
        Stream.iterate(0, n -> n + 2)
                .limit(5)
                .forEach(System.out::println);
        System.out.println("Stream Demo: Fibbonacci series using iterate method");
        Stream.iterate(new int[]{0,1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .map(t -> t[0])
                .forEach(System.out::print);

        System.out.println("Stream Demo: Generate 5 random numbers using generate function");
        Stream.generate(Math::random )
                .limit(5)
                .forEach(System.out::println);

    }
}
