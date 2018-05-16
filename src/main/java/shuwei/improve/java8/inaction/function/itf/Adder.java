package shuwei.improve.java8.inaction.function.itf;
/**
 * @author shuwei
 * @version 创建时间：2017年11月16日 下午4:28:26
 * 函数式接口,只有一个默认方法的接口，不是函数式接口
 */
// @FunctionalInterface
public interface Adder {
    default int add(int a, int b) {
        return 1;
    }
    
}
