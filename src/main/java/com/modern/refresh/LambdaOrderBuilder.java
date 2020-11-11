package com.modern.refresh;

import java.util.function.Consumer;

public class LambdaOrderBuilder {
    private final Order order = new Order();

    private LambdaOrderBuilder() {
    }

    public static Order order(Consumer<LambdaOrderBuilder> consumer) {
        final LambdaOrderBuilder lambdaOrderBuilder = new LambdaOrderBuilder();
        consumer.accept(lambdaOrderBuilder);
        return lambdaOrderBuilder.order;
    }

    public void forCustomer(String customer) {
        order.setCustomer(customer);
    }

    public void buy(Consumer<TradeBuilder> consumer) {
        final TradeBuilder tradeBuilder = new TradeBuilder();
        consumer.accept(tradeBuilder);

        order.addTrade(tradeBuilder.trade);
    }

    public void sell(Consumer<TradeBuilder> consumer) {
        final TradeBuilder tradeBuilder = new TradeBuilder();
        consumer.accept(tradeBuilder);
        tradeBuilder.trade.setType(Trade.Type.SELL);
        order.addTrade(tradeBuilder.trade);
    }

    private static class TradeBuilder {
        private final Trade trade = new Trade();

        private TradeBuilder() {
        }

        public void stock(Consumer<StockBuilder> consumer) {
            final StockBuilder stockBuilder = new StockBuilder();
            consumer.accept(stockBuilder);
            trade.setStock(stockBuilder.stock);
        }

        public void quantity(int quantity) {
            trade.setQuantity(quantity);
        }

        public void price(double price) {
            trade.setPrice(price);
        }
    }


    private static class StockBuilder {
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
        final Order order1 = order(o -> {
            o.forCustomer("Vinayak");
            o.buy(t -> {
                t.quantity(100);
                t.price(213.00);
                t.stock(s -> {
                    s.symbol("IBM");
                    s.market("BSE");
                });
            });
            o.sell(t -> {
                t.quantity(80);
                t.price(2100.00);
                t.stock(s -> {
                    s.symbol("APPLE");
                    s.market("NSE");
                });
            });
        });
        System.out.println(order1);

    }

}
