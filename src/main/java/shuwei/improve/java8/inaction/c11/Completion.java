package shuwei.improve.java8.inaction.c11;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import shuwei.improve.java8.inaction.c11.code.Discount;
import shuwei.improve.java8.inaction.c11.code.Quote;
import shuwei.improve.java8.inaction.c11.code.Shop;

public class Completion {
    static List<Shop> shops = Arrays.asList(new Shop("One"), new Shop("Two"), new Shop("Three"), new Shop("Four"));

    private static final Random random = new Random();

    public static void randomDelay() {
        int delay = 500 + random.nextInt(2000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Stream<CompletableFuture<String>> findPricesStream(String product) {
        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), AvoidBlock.executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture
                        .supplyAsync(() -> Discount.applyDiscount(quote), AvoidBlock.executor)));
    }

    public static void thenAcceptUse() {
        long start = System.currentTimeMillis();
        CompletableFuture[] futures = findPricesStream("myPhone").map(f -> f.thenAccept(System.out::println))
                .toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(futures).join();
        System.out.println("耗时:" + (System.currentTimeMillis() - start));
    }

    // 某一个结束就xxx
    public static void thenAcceptUse2() {
        long start = System.currentTimeMillis();
        CompletableFuture[] futures = findPricesStream("myPhone").map(f -> f.thenAccept(s -> System.out.println(s + " (done in " 
                + (System.currentTimeMillis() - start) + " msecs)")))
                .toArray(size -> new CompletableFuture[size]);
        // CompletableFuture.allOf(futures).join();
        System.out.println("总耗时:" + (System.currentTimeMillis() - start));
    }

    public static void main(String[] args) {
        findPricesStream("abc").map(CompletableFuture::join).collect(Collectors.toList());
        // thenAcceptUse2();
    }
}
