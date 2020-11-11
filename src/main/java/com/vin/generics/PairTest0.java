package com.vin.generics;

public class PairTest0 {
    public static void main(String[] args) {
        Pair<String> stringPair = new Pair<>("Vinayak", "Karigar");

        System.out.println("First Name:: "+stringPair.getFirst());
        System.out.println("Second Name:: " + stringPair.getSecond());
    }
}
