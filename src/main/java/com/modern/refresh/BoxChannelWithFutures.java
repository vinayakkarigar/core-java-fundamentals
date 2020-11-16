package com.modern.refresh;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BoxChannelWithFutures {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Demo of Box Channel with Futures construct");
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        final long start = System.nanoTime();
        final Future<Integer> fut1 = executorService.submit(() -> ThreadUtilities.f(100));
        final Future<Integer> fut2 = executorService.submit(() -> ThreadUtilities.g(100));

//        assuming combining function is summing
        System.out.println("Sum: " + (fut1.get() + fut2.get()));
        final long duration = (System.nanoTime() - start) / 1_000_000_000L;
        System.out.println("Duration: "+duration);

//        executorService.shutdown();
    }
}
