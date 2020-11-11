package com.vin.generics;

/*
You cannot instantiate type variable i.e
expression like new T(..) , new T[...] or T.class are illegal
To overcome this you need to design your api in a such a way that you are passed
the Class object and you can use newinstance() method on it to create the generic object.
 */
public class TypeParameterInstantiation {

    public static void main(String[] args) {
        Pair<String> stringPair = makePair(String.class);

        String[] inputs = {"max", "min", "hellohowareyou", "x"};
        String[] minmax = minmax(inputs);

    }

    public static <T> Pair<T> makePair(Class<T> cls) {
        try {
            return new Pair<>(cls.newInstance(), cls.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T extends Comparable> T[] minmax(T... a) {
        Object[] mm = new Object[2];
        T min = a[0];
        T max = a[0];

        for (int i = 0; i < a.length; i++) {
            if (min.compareTo(a[i]) > 0 ) min = a[i];
            if (max.compareTo(a[i]) < 0 ) max = a[i];
        }
        mm[0] = min;
        mm[1] = max;

        return (T[]) mm;
    }
}
