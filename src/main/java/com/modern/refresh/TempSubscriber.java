package com.modern.refresh;

import java.util.concurrent.Flow;

public class TempSubscriber implements Flow.Subscriber<TempInfo> {

    private Flow.Subscription subscription;


    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1);
    }

    @Override
    public void onNext(TempInfo item) {
        System.out.println("Temperature Info: " + item);
//        System.out.println("Subscriber: "+Thread.currentThread().getName());
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.err.println(throwable.getMessage());
        subscription.request(1);
    }

    @Override
    public void onComplete() {
        System.out.println("Done!");
    }
}
