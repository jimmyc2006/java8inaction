package shuwei.improve.java8.inaction.c10optional;

import java.util.Optional;

/**
 * @author shuwei
 * @version 创建时间：2017年9月25日 上午8:23:07
 * 类说明
 */
public class Person {
    private int age;
    private Optional<Car> car;

    public int getAge() {
        return age;
    }

    public Optional<Car> getCar() {
        return car;
    }
}
