package com.modern.refresh;

import jdk.jfr.Threshold;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BehaviourParameterizationDemo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final ExecutorService executorService = Executors.newCachedThreadPool();
        final Future<String> fu = executorService.submit(() -> Thread.currentThread().getName());
        System.out.println("Hello");
        System.out.println("hihi"+fu.get());

        executorService.shutdown();
    }
}
