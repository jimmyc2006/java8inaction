package shuwei.improve.java8.inaction.optional;

import org.junit.Test;

import java.util.Optional;

import shuwei.improve.java8.inaction.c10optional.Car;
import shuwei.improve.java8.inaction.c10optional.Insurance;
import shuwei.improve.java8.inaction.c10optional.Person;

public class MapTest {

  /**
   * 原来的代码
   */
  @Test
  public void test1() {
    String name = null;
    Insurance insurance = getInsurance();
    if (insurance != null) {
      name = insurance.getName();
    }
  }


  /**
   * 使用optional后的代码
   */
  @Test
  public void test() {
    Insurance insurance = this.getInsurance();
    String s = Optional.ofNullable(insurance).map(Insurance::getName).orElse("default");
  }

  private Insurance getInsurance() {
    return null;
  }


  /**
   * 将原来很多串联的if null get转
   *
   */
  public void test2() {

  }

  /**
   * 声明方法接受一个Optional参数，或者将结果作为Optional类型返回，
   * 让你的同事或者未来你的方法的使用者，很清楚地知道它可以接受空值，或者它可能返回一个空值
   */
  public void test3() {

  }


  @Test
  public void test4() {

  }
  /**
   * 这段代码中，你对第一个Optional对象调用flatMap方法，如果它是个空值，传递给它的Lambda表达式不会执行，这次调用会直接返回一个空的Optional对象。
   * 反之，如果person对象存在，这次调用就会将其作为函数Function的输入，并按照与faltMap方法的约定返回一个Optional<Insurance>对象。
   * 这个函数的函数体会对第二个Optional对象执行map操作，如果第二个对象不包含car，函数Function就返回一个空的Optional对象，
   * 整个person和car对象都存在，作为参数传递给map方法的Lambda表达式能够使用这两个值安全地调用原始的findCheapestInsurance方法，
   * 完成期望的操作
   */
  public Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> personOption,
                                                           Optional<Car> car) {
    return personOption.flatMap(p -> car.map(c -> findChapestInsurance(p, c)));
  }

  public Insurance findChapestInsurance(Person p, Car c) {
    return null;
  }
}
