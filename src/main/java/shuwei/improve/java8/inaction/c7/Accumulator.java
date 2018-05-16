package shuwei.improve.java8.inaction.c7;

import java.util.stream.LongStream;

public class Accumulator {
    public static long sideEffectSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).forEach(accumulator::add);
        return accumulator.total;
    }

    public static long sideEffectSumParallel(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }

    public long total = 0;

    public void add(long value) {
        total += value;
    }

    public static void main(String[] args) {
        System.out.println("sequentialSum " + PTest.measureSumPerf(Accumulator::sideEffectSumParallel, 10_000_000) + " msecs");
    }
}
