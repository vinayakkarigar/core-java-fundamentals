package com.modern.refresh;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Order {
    private String customer;
    private List<Trade> trades = new ArrayList<>();

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<Trade> getTrades() {
        return trades;
    }

    public void setTrades(List<Trade> trades) {
        this.trades = trades;
    }

    public double getValue() {
        return trades.stream()
                .mapToDouble(Trade::getValue).sum();
    }

    public void addTrade(Trade trade) {
        trades.add(trade);
    }
}
