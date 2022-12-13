package generics;

import java.util.ArrayList;
import java.util.Arrays;

public class PairAlgo {
    public static <T> void swapHelper(Pair<T> pair) {
        T t = pair.getFirst();
        pair.setFirst(pair.getSecond());
        pair.setSecond(t);
    }

    public static void swap(Pair<?> pair) {
        swapHelper(pair);
    }

    public static void minmaxbonus(Manager[] managers, Pair<? super Manager> result) {
        result.setFirst(managers[0]);
        result.setSecond(managers[1]);
        Pair<?> pairs = result;
//        swap(pairs);
    }

    public static void main(String[] args) {
        ArrayList<Manager> managers = new ArrayList<>();
        managers.add(0, new Manager(1));
        managers.add(1, new Manager(2));
        Manager[] managers1 = managers.toArray(new Manager[2]);
        Pair<Manager> employeePair = new Pair<>();
        minmaxbonus(managers1, employeePair);
        System.out.println(employeePair.getFirst());
        System.out.println(employeePair.getSecond());
    }
}
