package com.modern.refresh;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

public interface MyList<T> {
    T head();
    MyList<T> tail();

    default boolean isEmpty() {
        return true;
    }

    default MyList<T> filter(Predicate<T> p) {
        return (isEmpty()) ? this
                : p.test(head()) ? new MyLazyList<T>(head(), () -> tail().filter(p))
                : tail().filter(p);

    }

    public static class EmptyList<T> implements MyList<T> {

        @Override
        public T head() {
            throw new UnsupportedOperationException();
        }

        @Override
        public MyList<T> tail() {
            throw new UnsupportedOperationException();
        }
    }

    public static class MyLinkedList<T> implements MyList<T> {

        private T head;
        private MyList<T> tail;

        public MyLinkedList(T head, MyList<T> tail) {
            this.head = head;
            this.tail = tail;
        }

        @Override
        public T head() {
            return null;
        }

        @Override
        public MyList<T> tail() {
            return null;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }
    }

    public static class MyLazyList<T> implements MyList<T> {
        private T head;
        private Supplier<MyList<T>> tail;
        private Optional<MyList<T>> alreadyComputed = Optional.empty();

        public MyLazyList(T head, Supplier<MyList<T>> tail) {
            this.head = head;
            this.tail = tail;
        }

        @Override
        public T head() {
            return head;
        }

        @Override
        public MyList<T> tail() {
            alreadyComputed.ifPresent(i -> {
                System.out.println("I am already present" + i);
            });
            alreadyComputed = alreadyComputed.or(() -> Optional.of(tail.get()));
            return alreadyComputed.get();

        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        public static MyList<Integer> from(Integer integer) {
            return new MyLazyList<>(integer, () -> from(integer + 1));
        }

        public static MyList<Integer> primes(MyList<Integer> numbers) {
            return new MyLazyList<>(numbers.head(),
                    () -> primes(numbers.tail().filter(i -> i % numbers.head() != 0)));
        }

        public static <T> void printAll(MyList<T> list) {
            if (!list.isEmpty()) {
                System.out.println(list.head());
                printAll(list.tail());
            }
        }

    }


    public static void main(String[] args) {
        final MyLinkedList<Integer> integerMyLinkedList
                = new MyLinkedList<>(1, new MyLinkedList<>(2, new EmptyList<>()));

        final MyList<Integer> from = MyLazyList.from(2);
        final Integer int1 = from.head();
        final Integer int2 = from.tail().head();
        final Integer int3 = from.tail().tail().head();
        System.out.println("--" + int1 + "--" + int2 + "--" + int3);

        final MyList<Integer> primes = MyLazyList.primes(from);
        System.out.println(primes.head());
        System.out.println(primes.tail().head());
        System.out.println(primes.tail().tail().head());

        MyLazyList.printAll(primes);

    }

}

