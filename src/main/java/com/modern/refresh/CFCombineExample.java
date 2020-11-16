package com.modern.refresh;

import java.util.Comparator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CFCombineExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(8);
        final CompletableFuture<Integer> cf1 = new CompletableFuture<>();
        final CompletableFuture<Integer> cf2 = new CompletableFuture<>();
        final CompletableFuture<Integer> cf3 = cf1.thenCombine(cf2, (i, j) -> i + j);
        final long start = System.nanoTime();
        executorService.submit(() -> cf1.complete(ThreadUtilities.f(100)));
        executorService.submit(() -> cf2.complete(ThreadUtilities.g(100)));
        System.out.println("Sum: "+ cf3.get());
        final long duration = (System.nanoTime() - start) / 1_000_000_000L;
        System.out.println("Duration: "+duration);
    }
}
