package com.vin.bitmagic;

public class CheckBitDemo {

    public static boolean checkKthBitwise(int n, int k) {
        if ((n >> (k-1) & 1) !=  0) return true;
        return false;
    }


    public static void main(String[] args) {
        System.out.println(checkKthBitwise(4,1));
    }
}
