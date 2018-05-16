package shuwei.improve.java8.inaction.c10optional;

import java.util.Optional;

import org.junit.Test;

/**
 * @author shuwei
 * @version 创建时间：2017年9月25日 上午8:26:49
 * 类说明
 */
public class Demo {
    
    /**
     * 10.3.2
     * 原有流程
     */
    @Test
    public void test1032() {
        //原有流程
        Insurance ins = new Insurance();
        String name = null;
        if(ins != null) {
            name = ins.getName();
        }
        // 新流程
        Optional<Insurance> optInsurance = Optional.ofNullable(ins);
        Optional<String> nam = optInsurance.map(Insurance::getName);
        
    }
    
    // optional和序列化一块用
    static class Person2 {
        private Car car;
        public Optional<Car> getCarAsOptional() {
            return Optional.ofNullable(car);
        }
    }
    // 新方法,这个方法对序列化有影响
    public void test1033() {
        Person person = new Person();
        Optional<Person> optPerson = Optional.of(person);
        Optional<String> name = optPerson.flatMap(Person::getCar).flatMap(Car::getInsurance).map(Insurance::getName);
    }
    // 1034
    // get()
    // orElse(T other)
    // orElseGet(Supplier<? extends T> other)是orElse的延时调用版
    // orElseThrow(Supplier <? extends X> execptionSupplier)
    // ifPresent(Consumer<? super T)在变量存在时执行一个座位参数传入的方法
    
    public static void main(String[] args) {
        // 创建一个空的optional对象
        Optional<Car> optCar = Optional.empty();
        // 使用一个值初始化,不允许null
        Car carTest = new Car();
        Optional<Car> optCar2 = Optional.of(carTest);
        // 可接受null的Optional,将返回Optional.empty
        Car carTest2 = null;
        Optional<Car> optCar3 = Optional.ofNullable(carTest2);
    }
}
