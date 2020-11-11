package com.modern.refresh;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerDemo {

    public static <T> void foreach(List<T> list, Consumer<T> c) {
        for (T t : list) {
            c.accept(t);
        }
    }

    public static void main(String[] args) {
        final List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        foreach(integers, integer -> System.out.println(integer));
    }

}
