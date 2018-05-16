package shuwei.improve.java8.inaction.c10optional;

import java.util.Optional;

/**
 * @author shuwei
 * @version 创建时间：2017年9月25日 上午8:23:38
 * 类说明
 */
public class Car {
    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }
}
