package shuwei.improve.java8.inaction.c11;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import shuwei.improve.java8.inaction.c11.code.Discount;
import shuwei.improve.java8.inaction.c11.code.Quote;
import shuwei.improve.java8.inaction.c11.code.Shop;

public class MutiAsync {
    static List<Shop> shops = Arrays.asList(new Shop("One"), new Shop("Two"), new Shop("Three"), new Shop("Four"));

    public static List<String> findPrices(String product) {
        // 第一个map和第三个map有延时操作
        return shops.stream().map(shop -> shop.getPrice(product)).map(Quote::parse).map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    // 这里如果后面不使用thenCompose而是使用thenApply，就不能反回List<CompletableFuture<String>>，无法获取结果
    public static List<String> findPricesCompletableFuture(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), AvoidBlock.executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture
                        .supplyAsync(() -> Discount.applyDiscount(quote), AvoidBlock.executor)))
                .collect(Collectors.toList());
        return priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }
    
    public static List<String> findPricesCompletableFuture2(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), AvoidBlock.executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture
                        .supplyAsync(() -> Discount.applyDiscount(quote), AvoidBlock.executor)))
                .collect(Collectors.toList());
        return priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }
    
    public static Future<Double> findPricesCompletableFuture3() {
        Future<Double> futurePriceInUSD = CompletableFuture.supplyAsync(() -> MockExchangeService.getPrice()).thenCombine(
                CompletableFuture.supplyAsync(() -> MockExchangeService.getRate()), (price, rate) -> price * rate);
        return futurePriceInUSD;
    }
    
    public static Future<Double> findPricesCompletableFuture4() {
        ExecutorService executor = Executors.newCachedThreadPool();
        final Future<Double> futureRate = executor.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return MockExchangeService.getRate();
            }
        });
        Future<Double> futurePriceInUSD = executor.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                double priceInEUR = MockExchangeService.getPrice();
                return priceInEUR * futureRate.get();
            }
        });
        return futurePriceInUSD;
    }
    
    public static void test() {
        long start = System.currentTimeMillis();
        List<String> results = findPricesCompletableFuture("myPhone27S");
        System.out.println("consume time " + (System.currentTimeMillis() - start));
    }
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // test();
        /*
        long start = System.currentTimeMillis();
        Future<Double> result = findPricesCompletableFuture3();
        System.out.println("result:" + result.get() + "consume:" + (System.currentTimeMillis() - start));
        */
        long start = System.currentTimeMillis();
        Future<Double> result = findPricesCompletableFuture4();
        System.out.println(result.get() + "， 耗时：" + (System.currentTimeMillis() - start));
    }
}
