package com.modern.refresh;

public class ParallelStreamDemo {
    public static void main(String[] args) {
        final int i = Runtime.getRuntime().availableProcessors();
        System.out.println(i);
    }
}
