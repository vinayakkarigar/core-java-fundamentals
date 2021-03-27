package com.modern.refresh;

import java.util.stream.IntStream;

public class LazyListDemo {

    public static IntStream numbers() {
        return IntStream.iterate(2, n -> n + 1);
    }

    public static int head(IntStream intStream) {
        return intStream.findFirst().getAsInt();
    }

    public static IntStream tail(IntStream intStream) {
        return intStream.skip(1);
    }

}
