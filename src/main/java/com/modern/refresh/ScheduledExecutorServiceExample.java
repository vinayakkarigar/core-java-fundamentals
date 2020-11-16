package com.modern.refresh;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class ScheduledExecutorServiceExample {

    public static void work1() {
        System.out.println("Hello from work 1");
    }

    public static void work2() {
        System.out.println("Hello from work 2");
    }

    public static void main(String[] args) {
        final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        work1();
        scheduledExecutorService.schedule(ScheduledExecutorServiceExample::work2, 10, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();

        Function<Integer, Integer> add1 = (i) -> i + 1;
        Function<Integer, Integer> dble = (i) -> i * 2;

        System.out.println(add1.andThen(dble).apply(2));

    }




}
