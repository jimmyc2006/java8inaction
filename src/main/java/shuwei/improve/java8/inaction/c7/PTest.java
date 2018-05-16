package shuwei.improve.java8.inaction.c7;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 代码来自java8inAction,p144
 * 目的：
 * 1.求和方法的并行版本比顺序版本要慢得多：
 * <1> iterate生成的是装箱对象，必须拆箱成数字才能求和
 * <2> 我们很难把iterate分成多个独立块来并行执行
 * @author shuwei
 *
 */
public class PTest {
    
    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration  = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if (duration < fastest) {
                fastest = duration;
            }
        }
        return fastest;
    }
    
    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).reduce(0L, Long::sum);
    }
    
    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 1L; i <= n; i++) {
            result += i;
        }
        return result;
    }
    
    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(0L, Long::sum);
    }
    
    public static long rangedSum(long n) {
        return LongStream.rangeClosed(1, n).reduce(0L, Long::sum);
    }
    
    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n).parallel().reduce(0L, Long::sum);
    }
    
    public static void main(String[] args) {
        System.out.println("sequentialSum " + measureSumPerf(PTest::sequentialSum, 10_000_000) + " msecs");
        System.out.println("iterativeSum " + measureSumPerf(PTest::iterativeSum, 10_000_000) + " msecs");
        System.out.println("parallelSum " + measureSumPerf(PTest::parallelSum, 10_000_000) + " msecs");
        System.out.println("rangedSum " + measureSumPerf(PTest::rangedSum, 10_000_000) + " msecs");
        System.out.println("parallelRangedSum " + measureSumPerf(PTest::parallelRangedSum, 10_000_000) + " msecs");
    }
}
