package com.vin.threads;

public class SimpleThreadDemo {

    public static void main(String[] args) {

        System.out.println("I am main thread my name is" + Thread.currentThread().getName());
//        Creating thread is very easy as shown below
        var thread = new Thread() {
            @Override
            public void run() {
                System.out.println("I am different thread my name is" + Thread.currentThread().getName());
            }
        };
        thread.start();
//        what happens if u call start method twice let see.
//        we got to trying to call a terminated thread will lead to IllegalThreadStateException
//        thread.start();

//        What happens if I try to call the run method directly. Lets see.
//        We saw it just runs the code in the current thread that is main thread.
//          thread.run();

//        Below code shows the more idiomatic way of creating the thread
        final var thread1 = new Thread(() -> {
            System.out.println("I am idiomatic thread, my name is " + Thread.currentThread().getName());
        });
        thread1.start();

//        Let see the processor details available to java
        System.out.println("Available processors: " + Runtime.getRuntime().availableProcessors());
        System.out.println("Total Memory: " + Runtime.getRuntime().totalMemory());
        System.out.println("Max Memory: "+Runtime.getRuntime().maxMemory());
        System.out.println("Free Memory: "+ Runtime.getRuntime().freeMemory());
        



    }

}
