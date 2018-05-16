package shuwei.improve.java8.inaction.c10optional;

import java.util.Optional;

import org.junit.Test;

/**
 * @author shuwei
 * @version 创建时间：2017年9月26日 上午8:46:32 类说明
 */
public class OptionalFilter {

    @Test
    public void oldFilter() {
        Insurance insurance = new Insurance("xxxx");
        if (insurance != null && "xxxx".equals(insurance.getName())) {
            System.out.println("ok");
        }
    }

    @Test
    public void newFilter() {
        Optional<Insurance> optInsurance = Optional.ofNullable(new Insurance("xxxx"));
        optInsurance.filter(insurance -> "xxxx".equals(insurance.getName())).ifPresent(
                x -> System.out.println(x + "ok")); // 这里这个x是insurance的实例
    }

    // 在Optional中过滤
    public String getCarInsuranceName(Optional<Person> person, int minAge) {
        return person.filter(p -> p.getAge() >= minAge).flatMap(Person::getCar).flatMap(Car::getInsurance)
                .map(Insurance::getName).orElse("UnKnown");
    }
}
