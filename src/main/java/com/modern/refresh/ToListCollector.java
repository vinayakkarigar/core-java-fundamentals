package com.modern.refresh;

import java.nio.charset.CharacterCodingException;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return (l1, l2) -> {
            l1.addAll(l2);
            return l1;
        };
    }


    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        final Set<Characteristics> identityFinish = Set.of(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT);
        return identityFinish;
    }

    public static void main(String[] args) {
        final List<Dish> dishes = Dish.getDishes();
        final List<Dish> collect = dishes.stream()
                .collect(new ToListCollector<Dish>());
        System.out.println(collect);
    }
}
