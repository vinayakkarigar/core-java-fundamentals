package com.modern.refresh.again;

import com.modern.refresh.Trader;
import com.modern.refresh.Transaction;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class TransactionsDemo {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brain = new Trader("Brain", "Cambridge");
        List<com.modern.refresh.Transaction> transactions = Arrays.asList(
                new com.modern.refresh.Transaction(brain, 2011, 300),
                new com.modern.refresh.Transaction(raoul, 2012, 1000),
                new com.modern.refresh.Transaction(raoul, 2011, 400),
                new com.modern.refresh.Transaction(mario, 2012, 710),
                new com.modern.refresh.Transaction(brain, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        System.out.println(">>>>> Find all transactions in 2011 and sort them in ascending order");
        List<Transaction> collect = transactions.stream().filter(t -> t.getYear() == 2011).sorted(comparing(t -> t.getValue())).collect(toList());
        collect.forEach(System.out::println);

        System.out.println(">>>> Unique cities where traders work");
        List<String> uniqueCities = transactions.stream().map(t -> t.getTrader().getCity()).distinct().collect(toList());
        System.out.println(uniqueCities);

        System.out.println("find all traders from cambridge and sort them by name");
        List<Trader> cambridgeTraders = transactions.stream().map(Transaction::getTrader).filter(trader -> trader.getCity().equals("Cambridge")).sorted(comparing(Trader::getName))
                .distinct().collect(toList());
        cambridgeTraders.forEach(System.out::println);

        System.out.println("String of all traders names sorted alpha");
        String traderNames = transactions.stream().map(Transaction::getTrader)
                .map(Trader::getName).distinct().sorted().collect(joining());
        System.out.println(traderNames);

        System.out.println("Are any trader based in milan");
        boolean milan = transactions.stream().map(t -> t.getTrader().getCity()).anyMatch(c -> c.equals("Milan"));
        System.out.println(milan);

        System.out.println("Print the value of all transactions from traders living in cambridge");
        transactions.stream().filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue).forEach(System.out::println);

        System.out.println("Highest value of all the transactions");
        OptionalInt highestValue = transactions.stream().mapToInt(t -> t.getValue()).reduce(Integer::max);
        highestValue.ifPresent(System.out::println);

        System.out.println("Transaction with the smallest value");
        Optional<Transaction> min = transactions.stream().reduce((t1, t2) -> t1.getValue() > t2.getValue() ? t2 : t1);
        min.ifPresent(System.out::println);
    }

}
