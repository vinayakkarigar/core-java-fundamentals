package com.modern.refresh;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.function.Consumer;


public class ReactiveProgrammingDemo {

    private static class SimpleCell implements Publisher<Integer>, Subscriber<Integer> {
        private int value;
        private String name;

        List<Subscriber> subscribers = new ArrayList<>();

        public SimpleCell(String name) {
            this.name = name;
        }

        @Override
        public void subscribe(Subscriber<? super Integer> subscriber) {
            subscribers.add(subscriber);
        }

        public void subscribe(Consumer<? super Integer> consumer) {
            subscribers.add(new Subscriber<Integer>() {
                @Override
                public void onSubscribe(Flow.Subscription subscription) {

                }

                @Override
                public void onNext(Integer item) {
                    consumer.accept(item);
                }

                @Override
                public void onError(Throwable throwable) {

                }

                @Override
                public void onComplete() {

                }
            });
        }

        private void notifyAllSubscribers() {
            subscribers.forEach(s -> s.onNext(this.value));
        }

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            System.out.println("On subscription method");
        }

        @Override
        public void onNext(Integer item) {
            this.value = item;
            System.out.println(this.name + ":" + this.value);
            notifyAllSubscribers();
        }

        @Override
        public void onError(Throwable throwable) {
            System.out.println("Error has occured !!");
        }

        @Override
        public void onComplete() {
            System.out.println("On complete method");
        }
    }

    private static class ArithmeticCell extends SimpleCell {
        private int left;
        private int righ;

        public ArithmeticCell(String name) {
            super(name);
        }

        public void setLeft(Integer left) {
            this.left = left;
            onNext(left + this.righ);
        }

        public void setRigh(Integer right) {
            this.righ = right;
            onNext(this.righ + this.left);
        }
    }


    public static void main(String[] args) {
        System.out.println("Reactive Programming Demo with simple example");
        final SimpleCell c1 = new SimpleCell("C1");
        final SimpleCell c2 = new SimpleCell("C2");
        final ArithmeticCell c3 = new ArithmeticCell("C3");
        final SimpleCell c4 = new SimpleCell("C4");
        final ArithmeticCell c5 = new ArithmeticCell("C5");
        c1.subscribe(c3::setLeft);
        c2.subscribe(c3::setRigh);
        c3.subscribe(c5::setLeft);
        c4.subscribe(c5::setRigh);


        c1.onNext(10);
        c2.onNext(20);
        c4.onNext(30);



    }

}
