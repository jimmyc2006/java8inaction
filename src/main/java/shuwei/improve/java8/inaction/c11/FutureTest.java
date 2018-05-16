package shuwei.improve.java8.inaction.c11;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureTest {
    public static void test() {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Double> future = executor.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return doSomeLongComputation();
            }
        });
        doSomethingElse();
        try {
            Double result = future.get(1, TimeUnit.SECONDS);
            System.out.println("从主线程获得到的结果:" + result);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println("ExecutionException");
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("TimeoutException");
            e.printStackTrace();
        }
    }
    
    public static Double doSomeLongComputation() {
        System.out.println("start doSomeLongComputation");
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("doSomeLongComputation ready return");
        return System.currentTimeMillis() / 10000.0;
    }
    
    public static void doSomethingElse() {
        System.out.println("start doSomethingElse");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end doSomethingElse");
    }
    
    public static void main(String[] args) {
        test();
    }
}
