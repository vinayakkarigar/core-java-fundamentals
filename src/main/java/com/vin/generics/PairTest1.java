package com.vin.generics;

public class PairTest1 {

    public static void main(String[] args) {
        String[] strings = {"Mary", "a", "file", "zilla"};
        final Pair<String> minmax = ArrayAlg.minmax(strings);
        System.out.println("Minimum ::: " + minmax.getFirst());
        System.out.println("Maximum ::: " + minmax.getSecond());

//        trying array of parameterized types
        Pair<String>[] paraArray; //declaration is allowed
        paraArray = new Pair[10];  // raw type is allowed
//        paraArray = new Pair<String>[10];  // not allowed
        paraArray = (Pair<String>[]) new Pair<?>[10];
        Object[] objs = paraArray;
        objs[0] = new Pair<Employee>();





    }

}

class ArrayAlg {
    /**
     * Gets the minimum and maximum of an array of strings.
     * @param a
     * @return a pair with minimum and maximum value or null if a is empty or null
     */
    public static Pair<String> minmax(String[] a) {
        if (a == null || a.length == 0) {
            return null;
        }
        String min = a[0];
        String max = a[0];

        for (int i = 1; i < a.length; i++) {
            if (min.compareTo(a[i]) > 0) min = a[i];
            if (max.compareTo(a[i]) < 0) max = a[i];
        }

        return new Pair<>(min, max);

    }

}
