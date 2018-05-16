package shuwei.improve.java8.inaction.c11;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class AsyncTest {
    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    private static Random random = new Random(System.currentTimeMillis());

    private double calculatePrice(String product) {
        delay();
        throw new RuntimeException("ttt");
        // return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }
    
    /**
     * 将同步方法转换为异步方法,体现了返回Future的方法是如何写的
     */
    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            double price = calculatePrice(product);
            futurePrice.complete(price);
        }).start();
        return futurePrice;
    }
    
    /**
     * 异步带错误处理的CompletableFuture
     * @param product
     * @return
     */
    public Future<Double> getPriceAsyncException(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price);
            } catch (Exception ex) {
                futurePrice.completeExceptionally(ex);
            }
            
        }).start();
        return futurePrice;
    }
    
    // 工厂方法创建Future
    public Future<Double> getPriceAsyncFactory(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }
    
    public static void asyncTest() {
        AsyncTest shop = new AsyncTest();
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsyncFactory("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + " msecs");
        FutureTest.doSomethingElse();
        try {
            double price = futurePrice.get(2, TimeUnit.SECONDS);
            System.out.printf("Price is %.2f%n", price);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("--------");
            e.printStackTrace();
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }
    
    public static void main(String[] args) {
        asyncTest();
    }
}
