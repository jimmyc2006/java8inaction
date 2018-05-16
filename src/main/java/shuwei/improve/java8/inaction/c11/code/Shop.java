package shuwei.improve.java8.inaction.c11.code;

import java.util.Random;

import shuwei.improve.java8.inaction.c11.Completion;

public class Shop {

    private final String name;
    private final Random random;

    public Shop(String name) {
        this.name = name;
        random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
    }

    public String getPrice(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return name + ":" + price + ":" + code;
    }

    public double calculatePrice(String product) {
        System.out.println(System.currentTimeMillis() + ":" + name + " start sleep");
        Completion.randomDelay();
        System.out.println(System.currentTimeMillis() + ":" + name + " end sleep");
        return Util.format(random.nextDouble() * product.charAt(0) + product.charAt(1));
    }

    public String getName() {
        return name;
    }
}
