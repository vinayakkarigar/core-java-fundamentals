package com.modern.refresh;

import java.util.concurrent.*;

import static com.modern.refresh.ThreadUtilities.*;

public class ThreadExecutorServiceExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        Create an Executor service to submit your tasks
        final ExecutorService executorService = Executors.newFixedThreadPool(2);


        final long start = System.nanoTime();
        final Future<Integer> future1 = executorService.submit(() -> f(1000));
        final Future<Integer> future2 = executorService.submit(() -> g(1000));
        System.out.println("Sum: "+future1.get() + future2.get());
        final long duration = (System.nanoTime() - start) / 1_000_000_000L;
        System.out.println("Duration: "+duration);
        executorService.shutdown();

        System.out.println("Reduced BoilerPlate Code version using methods returning futures");
        final int i = asyncf(100).get() + asyncg(100).get();
        System.out.println(i);
    }
}
