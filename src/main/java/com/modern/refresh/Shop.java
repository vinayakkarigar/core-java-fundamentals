package com.modern.refresh;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Shop {

    public static class Quote {
        private String shopname;
        private double price;
        private Discount.Code code;

        private Quote() {
        }

        public static Quote parse(String string) {
            final String[] split = string.split(":");
            final Quote quote = new Quote();
            quote.shopname = split[0];
            quote.price = Double.parseDouble(split[1]);
            quote.code = Discount.Code.valueOf(split[2]);
            return quote;
        }

        public String getShopname() {
            return shopname;
        }

        public double getPrice() {
            return price;
        }

        public Discount.Code getCode() {
            return code;
        }
    }

    public String getName() {
        return name;
    }

    private String name;
    private static Random random = new Random();

    private static final ExecutorService executorNew = Executors.newFixedThreadPool(100, (Runnable r) -> {
        final Thread thread = new Thread(r);
//            thread.setDaemon(true);
        return thread;
    });

    public Shop(String name) {
        this.name = name;
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    public String getPriceImproved(String product) {
        System.out.println("I am here");
        final double price = calculatePrice(product);
//        System.out.println("price::"+price);
        final Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", name, price, code);
    }

    public Future<Double> getPriceAsync(String product) {
        return CompletableFuture.supplyAsync (() -> calculatePrice("mobile"));

    }

    private double calculatePrice(String product) {
        randomDelay();
//        throw new RuntimeException("fsfdsf");
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    /*
    This method can be used to mimic long running computation of 1 second and more while simulating the
    working of the asynchronous systems and machines. Please be careful to add as many calls to be method as necessary
    to achieve the delay of requisite amount to your software or program.
     */
    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            System.out.println("I am being interrupted");
            e.printStackTrace();
        }
    }

    public static int count = 0;

    public static void randomDelay() {
        final long delay = 500L + random.nextInt(2000);
//        System.out.println(delay);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            count++;
            System.out.println("I am being interrupted "+count);
//            e.printStackTrace();
        }

    }

    public static List<String> findPrices(List<Shop> shops) {
/*
        return shops.stream().parallel()
                .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice("mobile")))
                .collect(Collectors.toList());
*/
        Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(),
                100), (Runnable r) -> {
            final Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        });

        final int[] i = {0};

        final List<CompletableFuture<String>> mobile = shops.stream()
                .map(shop -> CompletableFuture
                        .supplyAsync(() -> String.format("%s price is %.2f", shop.getName(), shop.getPrice("mobile")), executor)
                .thenApply(s -> {
                    i[0]++;
                    System.out.println("I am complete now: "+i[0]);
                    return s;
                }))
                .collect(Collectors.toList());
        return mobile.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

    }

    public static List<String> findPricesWithDiscount(List<Shop> shops) {
        Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(),
                400), (Runnable r) -> {
            final Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        });

//        return shops.stream()
//                .map(shop -> shop.getPriceImproved("mobile"))
//                .map(Quote::parse)
//                .map(Discount::applyDiscount)
//                .collect(Collectors.toList());
//        return mobile;

        final List<CompletableFuture<String>> futures = shops.stream()
                .map(s -> CompletableFuture.supplyAsync(() -> s.getPriceImproved("mobile"),executor))
                .map(f -> f.thenApply(Quote::parse))
                .map(f -> f.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
                .collect(Collectors.toList());
        return futures.stream().map(CompletableFuture::join).collect(Collectors.toList());


    }

    public static Stream<CompletableFuture<String>> findPricesStream(List<Shop> shops) {

//        executor.shutdown();
        final int[] count = {0, 0};

        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> {
                    count[0]++;
                    System.out.println("I am inside 1st Async call"+count[0]);
                    System.out.println("Am I daemon::" +Thread.currentThread().isDaemon());
                    return shop.getPriceImproved("mobile");
                    } , Shop.executorNew).thenApply(s -> {
                        count[1]++;
                    System.out.println("I am complete now "+count[1]);
                    return s;
                }))
                .map(f -> f.thenApply(Quote::parse))
                .map(f -> f.thenCompose(q -> CompletableFuture.supplyAsync(() ->{
                    System.out.println("I am inside 2nd Async call");
                    return Discount.applyDiscount(q);
                } , Shop.executorNew)));
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Demo of the asynchronous version of the shop price query api");
//        create the shop object first.
/*        final Shop shop = new Shop("Best Shop");
        final long start = System.nanoTime();
//        invoke the method to get the price of the product.
        final Future<Double> mobile = shop.getPriceAsync("mobile");
        final long invocationTime = (System.nanoTime() - start) / 1_000_000L;
        System.out.println("Invocation time in milliseconds: " + invocationTime);
        System.out.println("I am doing something else now then waiting for the results to be available ");

        System.out.println("I want to check if the result is available now as its too late now " +
                "I dont have anything else useful to do now ");
        try {
            final Double price = mobile.get();
            System.out.println("Price is " + price);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println("I am here");
            e.printStackTrace();
        }
        final long retrivalTime = (System.nanoTime() - start) / 1_000_000L;
        System.out.println("Retrieval time in milliseconds: "+retrivalTime);
 */
        System.out.println("Number of available processes: " + Runtime.getRuntime().availableProcessors());

        final List<Shop> shops = List.of(new Shop("Reliance"),
                new Shop("Sangeeta"),
                new Shop("Purvika"),
                new Shop("Girias"),
                new Shop("Pai"),
                new Shop("Sangeeta1"),
                new Shop("Purvika1"),
                new Shop("Girias1"),
                new Shop("Pai1"),
                new Shop("Sangeeta2"),
                new Shop("Purvika2"),
                new Shop("Girias2"),
                new Shop("Pai2"),
                new Shop("Sangeeta3"),
                new Shop("Purvika3"),
                new Shop("Girias3"),
                new Shop("Pai3"),
                new Shop("Sangeeta4"),
                new Shop("Purvika4"),
                new Shop("Girias4"),
                new Shop("Pai4"),
                new Shop("Sangeeta5"),
                new Shop("Purvika5"),
                new Shop("Girias5"),
                new Shop("Pai5"),
                new Shop("Sangeeta6"),
                new Shop("Purvika6"),
                new Shop("Girias6"),
                new Shop("Pai6"),
                new Shop("Reliance"),
                new Shop("Sangeeta"),
                new Shop("Purvika"),
                new Shop("Girias"),
                new Shop("Pai"),
                new Shop("Sangeeta1"),
                new Shop("Purvika1"),
                new Shop("Girias1"),
                new Shop("Pai1"),
                new Shop("Sangeeta2"),
                new Shop("Purvika2"),
                new Shop("Girias2"),
                new Shop("Pai2"),
                new Shop("Sangeeta3"),
                new Shop("Purvika3"),
                new Shop("Girias3"),
                new Shop("Pai3"),
                new Shop("Sangeeta4"),
                new Shop("Purvika4"),
                new Shop("Girias4"),
                new Shop("Pai4"),
                new Shop("Sangeeta5"),
                new Shop("Purvika5"),
                new Shop("Girias5"),
                new Shop("Pai5"),
                new Shop("Sangeeta6"),
                new Shop("Purvika6"),
                new Shop("Girias6"),
                new Shop("Pai6"),
                new Shop("Reliance"),
                new Shop("Sangeeta"),
                new Shop("Purvika"),
                new Shop("Girias"),
                new Shop("Pai"),
                new Shop("Sangeeta1"),
                new Shop("Purvika1"),
                new Shop("Girias1"),
                new Shop("Pai1"),
                new Shop("Sangeeta2"),
                new Shop("Purvika2"),
                new Shop("Girias2"),
                new Shop("Pai2"),
                new Shop("Sangeeta3"),
                new Shop("Purvika3"),
                new Shop("Girias3"),
                new Shop("Pai3"),
                new Shop("Sangeeta4"),
                new Shop("Purvika4"),
                new Shop("Girias4"),
                new Shop("Pai4"),
                new Shop("Sangeeta5"),
                new Shop("Purvika5"),
                new Shop("Girias5"),
                new Shop("Pai5"),
                new Shop("Sangeeta6"),
                new Shop("Purvika6"),
                new Shop("Girias6"),
                new Shop("Pai6"),
                new Shop("Reliance"),
                new Shop("Sangeeta"),
                new Shop("Purvika"),
                new Shop("Girias"),
                new Shop("Pai"),
                new Shop("Sangeeta1"),
                new Shop("Purvika1"),
                new Shop("Girias1"),
                new Shop("Pai1"),
                new Shop("Sangeeta2"),
                new Shop("Purvika2"),
                new Shop("Girias7"),
                new Shop("Girias7"),
                new Shop("Girias7")
        );
        System.out.println("Number of shops: "+shops.size());
        final long start = System.nanoTime();
//        final List<String> prices = findPricesWithDiscount(shops);

//        final List<CompletableFuture<String>> collect = findPricesStream(shops).collect(Collectors.toList());
//        final List<String> prices = collect.stream()
//                                    .map(CompletableFuture::join)
//                                    .collect(Collectors.toList());

        final CompletableFuture[] completableFutures = findPricesStream(shops)
                .map(f -> f.thenAccept(s -> System.out.println( s + "( done in " + (System.nanoTime() - start) / 1_000_000L +" msecs)")))
                .toArray(size -> new CompletableFuture[size]);

        CompletableFuture.allOf(completableFutures)
                .thenAccept(v -> {System.out.println("Prices have been received from all the shops ( done in"
                        + (System.nanoTime() - start) / 1_000_000L +" msecs)");
//                executor.shutdown();
                });
        System.out.println("I reached this stage");
//        Thread.sleep(550);
        final List<Runnable> runnables = executorNew.shutdownNow();
        executorNew.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("Runnable: "+ runnables);
        final long duration = (System.nanoTime() - start) / 1_000_000L;
        System.out.println("Time Take: " + duration+" msecs");
//       System.out.println("Prices: "+prices);
    }

}
