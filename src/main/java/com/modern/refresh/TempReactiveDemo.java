package com.modern.refresh;

import java.util.concurrent.Flow;

public class TempReactiveDemo {
    public static void main(String[] args) {

        System.out.println("Temp reactive demo");
//        create a publisher for the temp info
        final Flow.Publisher<TempInfo> bangaloreTemp = getCelsiusTemp("Bangalore");
        bangaloreTemp.subscribe(new TempSubscriber());
    }

    public static Flow.Publisher<TempInfo> tempInfoPublisher(String town) {
        return  subscriber -> {
            subscriber.onSubscribe(new TempSubscription(town, subscriber));
        };
    }

    public static Flow.Publisher<TempInfo> getCelsiusTemp(String town) {
        return subscriber -> {
            final TempProcessor tempProcessor = new TempProcessor();
            tempProcessor.subscribe(subscriber);
            tempProcessor.onSubscribe(new TempSubscription(town, tempProcessor));
        };
    }

}
