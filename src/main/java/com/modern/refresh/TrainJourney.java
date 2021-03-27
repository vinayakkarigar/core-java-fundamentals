package com.modern.refresh;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class TrainJourney {

    private final int price;
    private final TrainJourney onward;

    public TrainJourney(int price, TrainJourney onward) {
        this.price = price;
        this.onward = onward;
    }

    public int getPrice() {
        return price;
    }

    public TrainJourney getOnward() {
        return onward;
    }

    public static TrainJourney append(TrainJourney a, TrainJourney b) {
        return a == null ? b : new TrainJourney(a.price, append(a.onward, b));
    }

    public static void printTrainJourney(TrainJourney trainJourney) {
        StringBuilder s = new StringBuilder();
        TrainJourney t = trainJourney;
        while (t != null ){
            s.append("->" + t.getPrice());
            t = t.onward;
        }
        System.out.println(s.toString());
    }

    public static void main(String[] args) {
        final TrainJourney trainJourney1 = new TrainJourney(1, new TrainJourney(2,
                new TrainJourney(3, null)));
        final TrainJourney trainJourney2 = new TrainJourney(4, new TrainJourney(5,
                new TrainJourney(6, null)));
        final TrainJourney trainJourney3 = TrainJourney.append(trainJourney1, trainJourney2);

        System.out.println("TrainJourney1::");
        printTrainJourney(trainJourney1);
        System.out.println("TrainJourney2::");
        printTrainJourney(trainJourney2);
        System.out.println("TrainJourney3::");
        printTrainJourney(trainJourney3);


    }

}
