package shuwei.improve.java8.inaction.c7;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    private static final long serialVersionUID = 1L;

    private final long[] numbers;
    private final int start;
    private final int end;

    public static final long THRESHOLD = 10000;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        System.out.println("start:" + start + ", end:" + end + ",length:" + length);
        if (length <= THRESHOLD) {
            return computeSequentially();
        }
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        leftTask.fork();
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
        Long rightResult = rightTask.compute(); // 这里貌似有问题，调用这个方法是否能并发？，应该没有问题，上面调用fork的时候，leftTask就开始执行了,这两个线程是并发的
        // 如果任务多了，也可以并发,但是二叉树类型的并发
        System.out.println("compute after");
        Long leftResult = leftTask.join();  // 如果上面的方法没有并发，这里怎么保证rightTask已经执行完了？
        return leftResult + rightResult;
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            try {
                Thread.sleep(1000);
                System.out.println(System.currentTimeMillis() + ":" + start + ":sleep");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sum += numbers[i];
        }
        return sum;
    }
    
    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }
    
    public static void main(String[] args) {
        forkJoinSum(100000);
    }
}
