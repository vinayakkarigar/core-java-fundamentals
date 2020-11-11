package com.modern.refresh;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    private long[] numbers;
    private int start;
    private int end;

    private final int TRESHOLD = 10_0000;

//    public constructor for creating main task
    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }
//  private constructors for creating the sub-tasks
    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

//    process elements of collection sequentially
    private long sequentialCombine() {
        long result = 0;
        for (int i = start; i < end; i++) {
            result += numbers[i];
        }
        return result;
    }

    @Override
    protected Long compute() {

        int length = end - start;

//        check if the length of collection is greater than threshold
        if (length <= TRESHOLD) {
            return sequentialCombine();
        }

//        split the task into sub tasks
//        System.out.println("start:"+start+" end:"+start+length/2);
        final ForkJoinSumCalculator leftTask =
                new ForkJoinSumCalculator(numbers, start, start + length/2);
        leftTask.fork();

        final ForkJoinSumCalculator rightTask =
                new ForkJoinSumCalculator(numbers, start + length/2, end);
        final Long rightResult = rightTask.compute();
        final Long leftResult = leftTask.join();

        return leftResult + rightResult;
    }

    public static long forkJoinSum(Long n) {
        final long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> forkJoinTask = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(forkJoinTask);
    }



}
