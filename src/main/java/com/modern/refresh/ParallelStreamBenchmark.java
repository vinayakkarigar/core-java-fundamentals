package com.modern.refresh;

import org.openjdk.jmh.annotations.*;

import java.sql.SQLOutput;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 2, jvmArgs = {"-Xms4G", "-Xmx4G"})
public class ParallelStreamBenchmark {

    public static final long N = 10_000_000;

/*    @Benchmark
    public long sequentialSum() {
//        return Stream.iterate(1L, i -> i + 1).limit(N)
//                .reduce(0L, Long::sum);

        return LongStream.rangeClosed(1, N)
                .reduce(0L, Long::sum);
    }*/


    /* @Benchmark
    public long iterativeSum() {
        long result = 0L;
        for (long i = 1; i <= N; i++) {
            result +=i;
        }
        return result;
    }

     */


    @Benchmark
    public long parallelSum() {
//        return Stream.iterate(1L, i -> i + 1).limit(N)
//                .parallel()
//                .reduce(0L, Long::sum);
        return LongStream.rangeClosed(1, N)
                .parallel()
                .reduce(0L, Long::sum);
    }


    /*    @TearDown(Level.Invocation)
        public void teardown() {
            System.gc();
        }
    */
    public static Long sideEffectParallelSum(Long number) {
        final long[] numbers = LongStream.rangeClosed(1, number).toArray();
        final Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, number)
                .parallel()
                .forEach(accumulator::add);
        return accumulator.result;
    }

    public static <T, R> long measurePerf(Function<T, R> func, T input) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            final long start = System.nanoTime();
            final R result = func.apply(input);
            final long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result::" + result);
            if (duration < fastest) {
                fastest = duration;
            }
        }
        return fastest;

    }

public static void main(String[] args) {

    System.out.println("SideEffect parallel sum done in: "
            + measurePerf(ParallelStreamBenchmark::sideEffectParallelSum, 10_000_000L) + " msecs");
    System.out.println("ForkJoing sum in "+measurePerf(ForkJoinSumCalculator::forkJoinSum,10_000_000L)+"msecs");
}

}

class Accumulator {
    public long result = 0;

    public void add(long n) {
        result += n;
    }

}

