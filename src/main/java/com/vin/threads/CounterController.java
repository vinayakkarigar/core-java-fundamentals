package com.vin.threads;

public class CounterController {
    public static final int REPEAT = 1_000_000;
    public static void main(String[] args) throws InterruptedException {
        final Counter counter = new Counter();
        Runnable r = () -> {
            for (int i = 0; i < REPEAT; i++) {
                System.out.println(Thread.currentThread().getName());
                counter.increment();
            }
        };

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("I am here");
        System.out.println(counter.count);

    }
}
