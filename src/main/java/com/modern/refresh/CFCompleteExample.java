package com.modern.refresh;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CFCompleteExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final long start = System.nanoTime();
        final CompletableFuture<Integer> cf1 = new CompletableFuture<>();
        final ExecutorService executorService = Executors.newFixedThreadPool(8);
        executorService.submit(() -> cf1.complete(ThreadUtilities.g(100)));
        int b = ThreadUtilities.f(100);
        int sum = cf1.get() + b;
        System.out.println("Sum: "+sum);
        final long dur = (System.nanoTime() - start) / 1_000_000_000L;
        System.out.println("Duration: "+dur);
        executorService.shutdown();
    }
}
