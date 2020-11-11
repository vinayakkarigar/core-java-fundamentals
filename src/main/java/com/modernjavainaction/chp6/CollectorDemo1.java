package com.modernjavainaction.chp6;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.modernjavainaction.chp6.Transaction.Currency.*;
import static java.util.stream.Collectors.*;

public class CollectorDemo1 {
    public static void main(String[] args) {
        final List<Transaction> transactions = List.of(
                new Transaction(INR, new Date(), 120),
                new Transaction(INR, new Date(), 400),
                new Transaction(INR, new Date(), 500),
                new Transaction(USD, new Date(), 1200),
                new Transaction(USD, new Date(), 1000),
                new Transaction(EURO, new Date(), 900),
                new Transaction(POUND, new Date(), 800),
                new Transaction(EURO, new Date(), 10000));

        final Map<Transaction.Currency, List<Transaction>> collect =
                transactions.stream().collect(groupingBy(Transaction::getCurrency));

        collect.keySet().forEach(System.out::println);

    }
}
