package com.modern.refresh;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamCompletePractiseDemo {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brain = new Trader("Brain", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brain, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(brain, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        System.out.println("“Find all transactions in the year 2011 and sort them by value (small to high)”\n");
        final List<Transaction> collect = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        collect.forEach(System.out::println);

        System.out.println("“What are all the unique cities where the traders work?”\n");
        final List<String> collect1 = transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        collect1.forEach(System.out::println);

        System.out.println("“Find all traders from Cambridge and sort them by name”\n");
        final List<Trader> cambridge = transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        cambridge.forEach(System.out::println);

        System.out.println("\n“Return a string of all traders’ names sorted alphabetically.”\n");
        transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce(String::concat)
                .ifPresent(System.out::println);

        System.out.println("“Are any traders based in Milan?”\n");
        final boolean milan = transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch(trader -> trader.getCity().equals("Milan"));
        System.out.println(milan);

        System.out.println("“Print the values of all transactions from the traders living in Cambridge”\n");
        transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(t -> t.getValue())
                .forEach(System.out::println);

        System.out.println("“What’s the highest value of all the transactions?”\n");
        transactions.stream()
                .map(t -> t.getValue())
                .reduce(Integer::max)
                .ifPresent(System.out::println);

        System.out.println("“Find the transaction with the smallest value.”\n");
//        transactions.stream()
//                .sorted(Comparator.comparing(Transaction::getValue))
//                .limit(1).collect(Collectors.toList()).forEach(System.out::println);
        transactions.stream()
                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2)
        .ifPresent(System.out::println);

        System.out.println("Calculate number of calories in the menu");
        final List<Dish> dishes = Dish.getDishes();
        dishes.stream()
                .map(Dish::getCalories)
                .reduce(Integer::sum).ifPresent(System.out::println);
    }
}
