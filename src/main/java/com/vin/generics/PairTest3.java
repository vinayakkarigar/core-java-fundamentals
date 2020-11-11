package com.vin.generics;

import java.nio.channels.Pipe;

public class PairTest3 {

    public static void main(String[] args) {
        System.out.println("Program Starts");

        Manager ceo = new Manager("Gus Greedy", 800000, 2019, 12, 29);
        Manager cfo = new Manager("Sid Sneaky", 500000, 2019, 12, 29);
        Pair<Manager> buddies = new Pair<Manager>(ceo, cfo);
        printBuddies(buddies);
        Pair<? extends Employee> wildcardBuddies = buddies;
        Employee wildcardBuddiesFirst = wildcardBuddies.getFirst();

        ceo.setBonus(100000);
        cfo.setBonus(600000);
        Manager[] a = {ceo, cfo};
        Pair<Employee> result = new Pair<>();
        minmaxBonus(a, result);
        System.out.println("MIN MAX CALL RESULT");
        System.out.println("first: " + result.getFirst().getName()+" second: "+result.getSecond().getName());
        maxminBonus(a, result);
        System.out.println("MAX MIN CALL RESULT");
        System.out.println("first: " + result.getFirst().getName()+" second: "+result.getSecond().getName());
    }

    public static void printBuddies(Pair<? extends Employee> buddies) {
        Employee first = buddies.getFirst();
        Employee second = buddies.getSecond();
        System.out.println(first.getName() + " and " + second.getName() + " are buddies");
    }



    public static void minmaxBonus(Manager[] a, Pair<? super Manager> result) {
        if ( a == null || a.length == 0) return;
        Manager min = a[0];
        Manager max = a[0];

        for (int i = 0; i < a.length; i++) {
            if (min.getBonus() > a[i].getBonus()) min = a[i];
            if (max.getBonus() < a[i].getBonus()) max = a[i];
        }
        result.setFirst(min);
        result.setSecond(max);
    }

    public static void maxminBonus(Manager[] a, Pair<? super Manager> result) {
        minmaxBonus(a, result);
        PairAlg.swap(result);
    }
}

class PairAlg {

    public static boolean hasNulls(Pair<?> pair) {
        return pair.getFirst() == null || pair.getSecond() == null;
    }

    public static void swap(Pair<?> pair) {
        swapHelper(pair);
    }

    public static <T> void swapHelper(Pair<T> pair) {
        T t = pair.getFirst();
        pair.setFirst(pair.getSecond());
        pair.setSecond(t);
    }
}
