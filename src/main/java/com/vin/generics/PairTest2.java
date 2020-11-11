package com.vin.generics;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class PairTest2 {
    public static void main(String[] args) {
        GregorianCalendar[] birthdays = {
                new GregorianCalendar(1906, Calendar.DECEMBER, 9), // Grace Hopper
                new GregorianCalendar(1815, Calendar.DECEMBER, 10), //Ada Lovelace
                new GregorianCalendar(1903, Calendar.DECEMBER, 3), //John Von Neumann
                new GregorianCalendar(1910, Calendar.JUNE, 22) //K Zuse
        };

        Pair<GregorianCalendar> minmax = ArrayAlg.minmax(birthdays);
        System.out.println("min="+ minmax.getFirst().getTime());
        System.out.println("max="+ minmax.getSecond().getTime());

    }

    static class ArrayAlg {
        /**
         * Gets the minimum and maximum from an array of objects of type T
         * @param a array of objects of type T
         * @param <T> type parameter
         * @return pair of minimum and maximum object or null if a is null or empty.
         */
        static <T extends Comparable<? super T>> Pair<T> minmax(T[] a) {
            if (a == null || a.length == 0) {
                return null;
            }
            T min = a[0];
            T max = a[0];

            for (T t : a) {
                if (min.compareTo(t) > 0) min = t;
                if (max.compareTo(t) < 0) max = t;
            }
            return new Pair<>(min, max);
        }

    }

}

