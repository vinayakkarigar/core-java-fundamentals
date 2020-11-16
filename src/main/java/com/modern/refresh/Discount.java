package com.modern.refresh;

import java.util.logging.SocketHandler;

public class Discount {
    public enum Code {
        NONE(0), SILVER(10), GOLD(15), PLATINUM(20);
        private final int percentage;
        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Shop.Quote quote) {
        return quote.getShopname() + " price is" + Discount.apply(quote.getPrice(), quote.getCode());
    }

    private static double apply(double price, Code code) {
        Shop.randomDelay();
        return price * (100 - code.percentage) / 100;
    }
}
