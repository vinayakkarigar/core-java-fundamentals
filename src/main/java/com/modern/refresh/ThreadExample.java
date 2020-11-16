package com.modern.refresh;

import com.sun.nio.sctp.ShutdownNotification;

public class ThreadExample {

    public static void main(String[] args) throws InterruptedException {
        int x = 1227;

        final long start = System.nanoTime();

        final Result result = new Result();
        final Thread thread1 = new Thread(() -> {
            result.left = 10000;
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        final Thread thread2 = new Thread(() -> {
            result.right = 100;
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.setDaemon(true);
        thread2.setDaemon(true);
        thread1.start();
        thread2.start();
//        thread1.join();
//        thread2.join();
        long duration = (System.nanoTime() - start)/1_000_000_000L;
        System.out.println("Duration:: "+duration);
        System.out.println("Sum:: "+result.left+result.right);


        Runtime.getRuntime().addShutdownHook(new Thread(
                () -> {
                    long dur = (System.nanoTime() - start)/1_000_000_000L;
                    System.out.println("Duration to shutdown:: "+dur);
                    System.out.println();
                }
        ));

    }



    private static class Result {
        private int left;
        private int right;
    }

}
