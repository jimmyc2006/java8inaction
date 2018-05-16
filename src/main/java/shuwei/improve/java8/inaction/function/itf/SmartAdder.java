package shuwei.improve.java8.inaction.function.itf;
/**
 * @author shuwei
 * @version 创建时间：2017年11月16日 下午4:31:28
 * 类说明
 */
@FunctionalInterface
public interface SmartAdder extends Adder {
    double add(double a, double b);
}
