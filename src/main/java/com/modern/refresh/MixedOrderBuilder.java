package com.modern.refresh;

import java.util.function.Consumer;
import java.util.stream.Stream;

import static com.modern.refresh.MixedOrderBuilder.TradeBuilder.*;

public class MixedOrderBuilder {
    private MixedOrderBuilder() {
    }



    public static Order order(String customer, TradeBuilder... tradeBuilders) {
        Order order = new Order();
        order.setCustomer(customer);
        Stream.of(tradeBuilders)
                .forEach(tb -> order.addTrade(tb.trade));
        return order;
    }

    public static class TradeBuilder {
        Trade trade = new Trade();

        private TradeBuilder() {
        }

        public static TradeBuilder buy(Consumer<TradeBuilder> consumer) {
            final TradeBuilder tradeBuilder = new TradeBuilder();
            consumer.accept(tradeBuilder);
            tradeBuilder.trade.setType(Trade.Type.BUY);
            return tradeBuilder;
        }

        public static TradeBuilder sell(Consumer<TradeBuilder> consumer) {
            final TradeBuilder tradeBuilder = new TradeBuilder();
            consumer.accept(tradeBuilder);
            tradeBuilder.trade.setType(Trade.Type.SELL);
            return tradeBuilder;
        }

        public void quantity(int quantity) {
            trade.setQuantity(quantity);
        }

        public void price(double price) {
            trade.setPrice(price);
        }

        public void stock(Consumer<StockBuilder> builderConsumer) {
            final StockBuilder stockBuilder = new StockBuilder();
            builderConsumer.accept(stockBuilder);
            trade.setStock(stockBuilder.stock);
        }

    }

    public static class StockBuilder {
        private final Stock stock = new Stock();

        private StockBuilder() {
        }

        public void symbol(String symbol) {
            stock.setSymbol(symbol);
        }

        public void market(String market) {
            stock.setMarket(market);
        }
    }

    public static void main(String[] args) {
        final Order order = order("Vinayak",
                buy(tb -> {
                    tb.quantity(100);
                    tb.price(123.00);
                    tb.stock(s -> {
                        s.symbol("IBM");
                        s.market("BSE");
                    });
                }),
                sell(tb -> {
                    tb.quantity(80);
                    tb.price(2100.00);
                    tb.stock(s -> {
                        s.symbol("APPLE");
                        s.market("NSE");
                    });
                }));

        System.out.println(order.getTrades());
        order.getTrades().stream().forEach( t-> System.out.println(t.getStock().getSymbol() + t.getPrice()));
    }
}
