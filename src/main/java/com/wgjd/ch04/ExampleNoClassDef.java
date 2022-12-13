package com.wgjd.ch04;

public class ExampleNoClassDef {
    public static class BadInit {
        private static int thisIsFine = 1 / 0;
    }

    public static void main(String[] args) {
        try {
            var init = new BadInit();
        } catch (Throwable throwable) {
            System.out.println(throwable);
        }
        try {
            var init2 = new BadInit();
        } catch (Throwable throwable) {
            System.out.println(throwable);
        }
    }

}
