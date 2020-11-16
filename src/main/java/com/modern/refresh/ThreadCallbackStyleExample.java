package com.modern.refresh;

public class ThreadCallbackStyleExample {
    public static void main(String[] args) {
        System.out.println("Callback style usage of the thread constructs");
        System.out.println("However this not working as expected there are improvements to be made");
        final Result result = new Result();
        ThreadUtilities.callbackf(100, i -> {
            result.left = i;
            System.out.println("sum::"+(result.left + result.right));
        });
        ThreadUtilities.callbackg(100, i -> {
            result.right = i;
            System.out.println("sum::"+(result.left + result.right));
        });
    }

    private static class Result {
        private int left;
        private int right;
    }
}
