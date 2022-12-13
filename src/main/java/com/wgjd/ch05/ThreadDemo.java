package com.wgjd.ch05;

public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Demo Java Multi Threading capabilities.");
        Runnable r = () -> {
            var start = System.currentTimeMillis();
            var thisThread = Thread.currentThread();
            try {
                Thread.sleep(1000);
                System.out.println("My name: "+ thisThread.getName() + " I am sleeping for "+ (System.currentTimeMillis() - start));

            } catch (InterruptedException e) {
                System.out.println("Exception encountered:"+e.getMessage());
            }
        };

        var t1 = new Thread(r);
        t1.start();
        Thread.sleep(100);
        System.out.println("Exiting the Main Process");
    }
}
