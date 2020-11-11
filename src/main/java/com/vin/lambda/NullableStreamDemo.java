package com.vin.lambda;

import java.util.stream.Stream;

public class NullableStreamDemo {

    public static void main(String[] args) {

//        System.getProperties().forEach( (k,v) -> System.out.println(k+" "+v));

        System.out.println(System.getenv("HOME"));
        Stream.of("config", "user.home", "user.name").flatMap(k -> Stream.ofNullable(System.getProperty(k)))
        .forEach(System.out::println);
    }
}
