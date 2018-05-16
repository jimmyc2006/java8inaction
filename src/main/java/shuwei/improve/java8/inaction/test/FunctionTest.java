package shuwei.improve.java8.inaction.test;

import java.util.function.Function;
import java.util.function.IntFunction;

/**
 * @author shuwei
 * @version 创建时间：2017年11月30日 上午9:38:37
 * 类说明
 */
public class FunctionTest {
    public static void main(String[] args) {
        Function<Integer, Integer> fx = x -> x + 1;
        Function<Integer, Integer> gx = x -> x * 2;
        System.out.println(cal(2, fx)); // 3
        System.out.println(cal(2, gx)); // 4
        System.out.println(cal(3, fx.andThen(gx))); // 8
        System.out.println(cal(3, fx.compose(gx))); // 7
    }
    
    public static int cal(int x, Function<Integer, Integer> func) {
        return func.apply(x);
    }
}
