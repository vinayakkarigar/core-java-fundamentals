package com.modern.refresh;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;

public class TempSubscription implements Flow.Subscription {

    private String town;
    private Flow.Subscriber<? super TempInfo> subscriber;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public TempSubscription(String town, Flow.Subscriber<? super TempInfo> subscriber) {
        this.town = town;
        this.subscriber = subscriber;
    }

    @Override
    public void request(long n) {
        executorService.submit( () -> {
            for (int i = 0; i < n; i++) {
                try {
//                    System.out.println("Request: "+Thread.currentThread().getName());
                    subscriber.onNext(TempInfo.fetch(town));
                }catch (Exception e){
                    subscriber.onError(e);
                    break;
                }
            }
        });

    }

    @Override
    public void cancel() {
        subscriber.onComplete();
    }
}
