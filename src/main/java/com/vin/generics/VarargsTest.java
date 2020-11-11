package com.vin.generics;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Varargs warning in case of array of generic type
 */
public class VarargsTest {

    public static void main(String[] args) {
        Collection<Pair<String>> table = new ArrayList<>();
        table.add(new Pair<>("a", "b"));
        table.add(new Pair<>("a1", "b1"));
        table.add(new Pair<>("a2", "b2"));
        table.add(new Pair<>("a3", "b3"));

        Pair<String> pair0 = new Pair<>("p0", "q0");
        Pair<String> pair1 = new Pair<>("p1", "q1");
        Pair<String> pair2 = new Pair<>("p2", "q2");
        Pair<String> pair3 = new Pair<>("p3", "q3");

        addAll(table, pair0, pair1, pair2, pair3);

        System.out.println(table);
    }

    @SafeVarargs
    private static <T> void addAll(Collection<T> coll, T... ts) {
        for (T i :ts) {
            coll.add(i);
        }
    }
}
