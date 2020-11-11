package com.vin.threads;

public class Counter {
    int count = 0;

    /**
     * Increment the count.
     */
    synchronized public void increment() {
        count += 1;
    }
}
