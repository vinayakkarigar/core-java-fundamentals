package com.vin.threads;

import java.util.Arrays;

public class SimpleThreadsDemo {
    public static void main(String[] args) throws InterruptedException {
//        final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
//        Arrays.stream(stackTrace).forEach(System.out::println);
        System.out.println(Thread.currentThread().getState());

        final Thread thread = new Thread(() -> System.out.println(Thread.currentThread().getId()));
        System.out.println(thread.getState());
        thread.start();
        Thread.sleep(1000);
        thread.start();
        System.out.println(thread.getState());


    }
}
