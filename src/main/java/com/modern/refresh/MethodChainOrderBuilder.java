package com.modern.refresh;

import javax.print.DocFlavor;

public class MethodChainOrderBuilder {

    /* Order order = forCustomer("VInayak")
                        .buy(80)
                        .stock("apple")
                            .on("BSE")
                        .at(122.00)
                        .sell(100)
                        .stock("IBM")
                            .on("NSE")
                        .at(2100.00)
     */

    public Order order = new Order();

    private MethodChainOrderBuilder(String customer) {
        this.order.setCustomer(customer);
    }

    public static MethodChainOrderBuilder forCustomer(String customer) {
        final MethodChainOrderBuilder methodChainOrderBuilder = new MethodChainOrderBuilder(customer);
        return methodChainOrderBuilder;
    }

    public TradeBuilder buy(int quantity) {
        return new TradeBuilder(this, Trade.Type.BUY, quantity);
    }

    public TradeBuilder sell(int quantity) {
        return new TradeBuilder(this, Trade.Type.SELL, quantity);
    }

    public MethodChainOrderBuilder addTrade(Trade trade) {
        this.order.addTrade(trade);
        return this;
    }

    public Order end() {
        return this.order;
    }

    private static class TradeBuilder {
        public MethodChainOrderBuilder builder;
        public  Trade trade = new Trade();

        private TradeBuilder(MethodChainOrderBuilder builder, Trade.Type type, int quantity) {
            this.builder = builder;
            trade.setQuantity(quantity);
            trade.setType(type);
        }

        public StockBuilder stock(String symbol) {
            final StockBuilder stockBuilder = new StockBuilder(builder, trade, symbol);
            return stockBuilder;
        }

    }

    private static class StockBuilder {
        private MethodChainOrderBuilder builder;
        private final Trade trade;
        public Stock stock = new Stock();

        private StockBuilder(MethodChainOrderBuilder builder, Trade trade, String symbol) {
            this.builder = builder;
            this.trade = trade;
            stock.setSymbol(symbol);
        }

        public TraderBuilderWithStock on(String market) {
            stock.setMarket(market);
            return new TraderBuilderWithStock(builder, trade, stock);
        }

    }

    private static class TraderBuilderWithStock {
        private final MethodChainOrderBuilder builder;
        private final Trade trade;
        private final Stock stock;

        private TraderBuilderWithStock(MethodChainOrderBuilder builder, Trade trade, Stock stock) {
            this.builder = builder;
            this.trade = trade;
            this.stock = stock;
        }

        public MethodChainOrderBuilder at(double price) {
            trade.setPrice(price);
            builder.addTrade(trade);
            return builder;
        }

    }


    public static void main(String[] args) {
        Order order = forCustomer("Vinayak")
                .buy(80)
                .stock("APPLE")
                    .on("BSE")
                .at(123.00)
                .sell(100)
                .stock("IBM")
                    .on("NSE")
                .at(2100.00)
                .end();
        System.out.println(order);

    }
}
