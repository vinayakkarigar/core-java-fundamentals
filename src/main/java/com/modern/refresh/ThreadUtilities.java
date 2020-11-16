package com.modern.refresh;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.IntConsumer;

public class ThreadUtilities {
    public static int f(int x) throws InterruptedException {
        Thread.sleep(5_000);
        return 2000;
    }

    public static int g(int x) throws InterruptedException {
        Thread.sleep(8_000);
        return 4000;
    }

    public static Future<Integer> asyncf(int x) throws InterruptedException {
        final CompletableFuture<Integer> future = new CompletableFuture<>();
        future.completeAsync(() -> {
            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 2000;
        });
        return future;

    }

    public static Future<Integer> asyncg(int x) throws InterruptedException {
        final CompletableFuture<Integer> future = new CompletableFuture<>();
        future.completeAsync(() -> {
            try {
                Thread.sleep(8_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 4000;
        });
        return future;

    }

    public static void callbackf(int x, IntConsumer dealWithResult ) {
        try {
            final Future<Integer> future = asyncf(x);
            dealWithResult.accept(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void callbackg(int x, IntConsumer dealWithResult ) {
        try {
            final Future<Integer> future = asyncg(x);
            dealWithResult.accept(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
