package shuwei.improve.java8.inaction.c10optional;

import java.util.Optional;

/**
 * @author shuwei
 * @version 创建时间：2017年9月26日 上午8:53:46
 * 类说明
 */
public class CheckNull {
    public Insurance findCheapestInsurance(Person person, Car car) {
        // 不同的保险公司提供的查询服务
        // 对比所有数据
        Insurance cheapestCompany = new Insurance();
        return cheapestCompany;
    }
    
    public Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car) {
        if(person.isPresent() && car.isPresent()) {
            return Optional.of(findCheapestInsurance(person.get(), car.get()));
        } else {
            return Optional.empty();
        }
    }
    
    // 这种用法,对两个参数为null的检查的代码的优化
    public Optional<Insurance> nullSafeFindCheapestInsurance2(Optional<Person> person, Optional<Car> car) {
        return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
    }
}
