package com.vin.threads;

public class ThreadExceptionHandlerDemo {
    public static void main(String[] args) {

        // This thread just throws the exception
        final Thread handledThread = new Thread(() -> {
            throw new UnsupportedOperationException();
        });

        //give a name for the thread
        handledThread.setName("My Broken thread");
/**/
        //Here the handler for the error
        handledThread.setUncaughtExceptionHandler((t, e) -> {
            System.err.println(e.toString());
            System.err.println(e.getStackTrace()[0].getLineNumber() + e.getStackTrace()[0].getFileName());
        });
        handledThread.start();
    }
}

