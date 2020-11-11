package com.vin.lambda;

import java.util.stream.IntStream;

public class BetterPythagorianTriple {
    public static void main(String[] args) {
        IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                            .mapToObj(b -> new double[]{a, b, Math.sqrt(a*a + b*b)})
                .filter(t -> t[2] % 1 == 0)
                ).limit(5).forEach(t -> System.out.println(t[0]+" "+t[1]+" "+t[2]));
    }

}
