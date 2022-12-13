package com.wgjd.ch05;

import java.util.concurrent.Executors;

public class NewDemo {
    public static void main(String[] args) {

        Runnable r1 = () -> {
            System.out.println("HI --------- 1");
        };
        Runnable r2 = () -> {
            System.out.println("HI ----------2 ");
        };
        var thread = new Thread(r1);
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        var cachedThreadPool = Executors.newCachedThreadPool();
    }
}
