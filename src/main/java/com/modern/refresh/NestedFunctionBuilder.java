package com.modern.refresh;

import java.util.stream.Stream;

public class NestedFunctionBuilder {

    public static Order order(String customer, Trade... trades) {
        final Order order = new Order();
        order.setCustomer(customer);
        Stream.of(trades)
                .forEach(trade -> order.addTrade(trade));
        return order;
    }

    public static Trade buy(int quantity, Stock stock, double price) {
        return buildTrade(quantity, stock, price, Trade.Type.BUY);
    }

    public static Trade sell(int quantity, Stock stock, double price) {
        return buildTrade(quantity, stock, price, Trade.Type.SELL);
    }

    private static Trade buildTrade(int quantity, Stock stock, double price, Trade.Type type) {
        final Trade trade = new Trade();
        trade.setQuantity(quantity);
        trade.setStock(stock);
        trade.setPrice(price);
        trade.setType(type);
        return trade;
    }

    public static Stock stock(String symbol, String market) {
        final Stock stock = new Stock();
        stock.setSymbol(symbol);
        stock.setMarket(market);
        return stock;
    }

    public static String on(String market) {
        return market;
    }

    public static double at(double price) {
        return price;
    }

    public static void main(String[] args) {
        final Order order = order("Vinayak",
                                    buy(100,
                                            stock("IBM", on("BSE")),
                                            at(123.00)),
                                    sell(80,
                                            stock("APPLE", on("NSE")),
                                            at(2100.00))
                            );
        System.out.println(order);
    }
}
