package com.modern.refresh;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

public class AtomicDemo {
    public static void main(String[] args) {
        System.out.println("Long Adder demo");
        final LongAdder longAdder = new LongAdder();
        longAdder.add(10);
        longAdder.add(5);
        System.out.println(longAdder.sum());

        System.out.println("Long Accumulator Demo");
        final LongAccumulator longAccumulator = new LongAccumulator(Long::sum, 0);
        longAccumulator.accumulate(10);
        longAccumulator.accumulate(15);
        System.out.println(longAccumulator.get());

        System.out.println("Demo static join method of the String class");
        final String join = String.join(" ", "Vinayak", "Rachana", "Karigar");
        System.out.println(join);
    }

}
