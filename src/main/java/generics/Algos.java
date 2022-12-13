package generics;

import com.modern.refresh.again.ParallelStreamBenchmark;

import java.util.ArrayList;
import java.util.Arrays;

public class Algos {
    public static <T extends Comparable> Pair<T> minmax(ArrayList<T> elements) {
        var min = elements.get(0);
        var max = elements.get(0);

        for (T e: elements) {
            if (min.compareTo(e) >= 0) {
                min = e;
            } else {
                max = e;
            }
        }
        return new Pair<>(min, max);
    }
}
