package com.modernjavainaction.chp6;

import java.util.Date;

public class Transaction {
    private Currency currency;
    private Date transactionDate;
    private long value;

    public Transaction(Currency currency, Date transactionDate, long value) {
        this.currency = currency;
        this.transactionDate = transactionDate;
        this.value = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public enum Currency {
        INR, USD, EURO, POUND, YEN
    }
}
