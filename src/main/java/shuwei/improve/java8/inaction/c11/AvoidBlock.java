package shuwei.improve.java8.inaction.c11;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.function.Function;
import java.util.stream.Collectors;

import shuwei.improve.java8.inaction.c11.code.Shop;

public class AvoidBlock {
    static List<Shop> shops = Arrays.asList(new Shop("One"), new Shop("Two"), new Shop("Three"), new Shop("Four"),
            new Shop("Five"), new Shop("Six"), new Shop("Seven"));

    public static List<String> findPrices(String product) {
        return shops.stream().map(shop -> String.format("%s price is %s", shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    public static List<String> findPricesParallel(String product) {
        return shops.stream().parallel()
                .map(shop -> String.format("%s price is %s", shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    public static void test(Function<String, List<String>> f) {
        long start = System.currentTimeMillis();
        System.out.println(f.apply("myPhone27S"));
        long duration = System.currentTimeMillis() - start;
        System.out.println(f.toString() + "Done in " + duration + " msecs");
    }

    // 返回Future列表以后，需要自己一个一个get
    public static List<CompletableFuture<String>> futureList1(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture
                        .supplyAsync(() -> String.format("%s price is %s", shop.getName(), shop.getPrice(product))))
                .collect(Collectors.toList());
        return priceFutures;
    }

    public static void testCompletableFuture() {
        long start = System.currentTimeMillis();
        List<CompletableFuture<String>> results = futureList1("myPhone27S");
        for (CompletableFuture<String> res : results) {
            try {
                String result = res.get();
                System.out.println(result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("duration time :" + (System.currentTimeMillis() - start));
    }

    // 将List<CompletableFuture<String>>转换为List<String>
    public static List<String> findPrices2(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream().map(
                shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is " + shop.getPrice(product), executor))
                .collect(Collectors.toList());
        return priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    public final static Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    });

    /**
     * <1> 在使用4个shop的情况下 使用parallel的时候耗时1秒 借用CompletableFuture以后，都是使用2秒 <2> 在使用5个shop的情况下
     * 使用parallel的时候耗时2变成了2秒 借用CompletableFuture以后，使用2秒 <3>
     * 7个的时候,parallel还是2秒（跟4的倍数相关），但是CompletableFuture变成了3秒(跟3的倍数相关),看源码能看到一个默认的pool，使用的是3
     */
    public static void main(String[] args) {
        // System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "8");
        // System.out.println("MMMMMMM:" + Runtime.getRuntime().availableProcessors());
        // test(AvoidBlock::findPrices);
        // test(AvoidBlock::findPricesParallel);
        // testCompletableFuture();
        test(AvoidBlock::findPrices2);
    }
}
