package com.modern.refresh;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class RxJavaDemo {
    public static void main(String[] args) throws InterruptedException {

//        final Observable<Long> perSecObs = Observable.interval(1, TimeUnit.SECONDS);
//        perSecObs.blockingSubscribe(
//                (i) -> System.out.println(TempInfo.fetch("BLR"))
//        );
//        Thread.sleep(10_000);

//        final Observable<TempInfo> blrTemp = getTemperature("BLR");
//        final Observable<TempInfo> blrTemp = getCelsiusTemperature("BLR");
//        final Observable<TempInfo> blrTemp = getNegativeCelsiusTemperature("BLR");
        final Observable<TempInfo> blrTemp = getCelsiusTemperatures("BLR", "SKV", "BGM", "HUB");
        blrTemp.blockingSubscribe(new TempObserver());




    }

    public static Observable<TempInfo> getTemperature(String town) {
        return Observable.create( observableEmitter -> {
            Observable.interval(1, TimeUnit.SECONDS)
                    .subscribe(i -> {
                        if (!observableEmitter.isDisposed()){
//                            System.out.println("1");
//                            System.out.println(i);
                            if (i >= 5) {
                                observableEmitter.onComplete();
                            }
                            else {
//                                System.out.println("HIHI");
                                try {
                                    observableEmitter.onNext(TempInfo.fetch(town));
                                } catch (Exception e) {
                                    observableEmitter.onError(e);
                                }
                            }

                        }

                    });
        } );
    }

    public static Observable<TempInfo> getCelsiusTemperature(String town) {
        return getTemperature(town)
                .map(t -> new TempInfo(t.getTown(), (t.getTemp() - 32) * 5/9));
    }

    public static Observable<TempInfo> getNegativeCelsiusTemperature(String town) {
        return getCelsiusTemperature(town)
                .filter(t -> t.getTemp() < 0);
    }

    public static Observable<TempInfo> getCelsiusTemperatures(String... towns) {
        final List<Observable<TempInfo>> collect = Arrays.stream(towns)
                .map(RxJavaDemo::getCelsiusTemperature)
                .collect(Collectors.toList());
        return Observable.merge(collect);
    }
}
