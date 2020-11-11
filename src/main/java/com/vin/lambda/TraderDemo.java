package com.vin.lambda;

import java.util.Comparator;
import java.util.List;

public class TraderDemo {

    public static void main(String[] args) {
        //prepare data
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        final List<Transaction> transactions = List.of(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        //1. find all the transactions in the year 2011 and sort them by value ( small to large)
        System.out.println("//1. find all the transactions in the year 2011 and sort them by value ( small to large)");
        transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue)).forEach(System.out::println);

        //2. What are all the unique cities where traders work.
        System.out.println("//2. What are all the unique cities where traders work.");
        transactions.stream().map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);

        //3. Find all the traders from Cambridge and sort them by name.
        System.out.println("//3. Find all the traders from Cambridge and sort them by name.");
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getTrader)
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);

        //4. Return a String of all trader names sorted alphabetically.
        System.out.println("//4. Return a String of all trader names sorted alphabetically.");
        final String reduce = transactions.stream()
                .map(Transaction::getTrader)
                .sorted(Comparator.comparing(Trader::getName))
                .map(Trader::getName)
                .distinct()
                .reduce("", ((s, s2) -> s + "," + s2));
        System.out.println(reduce);

        //5. Are any traders from Milan
        final boolean milan = transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch(trader -> trader.getCity().equals("Milan"));
        System.out.println(milan);

        //6. Print the values of all transactions from the traders living in Cambridge.
        System.out.println("//6. Print the values of all transactions from the traders living in Cambridge.");
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        //7. Whats the highest value of all the transactions
        System.out.println("//7. Whats the highest value of all the transactions");
        transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::compareTo).ifPresent(System.out::println);

        //8. Find the transaction with the smallest value.
        System.out.println("//8. Find the transaction with the smallest value.");
        transactions.stream()
                .min(Comparator.comparing(Transaction::getValue))
                .ifPresent(System.out::println);
    }
}
