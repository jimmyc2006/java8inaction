package shuwei.improve.java8.inaction.test;

import java.util.function.Function;

/**
 * @author shuwei
 * @version 创建时间：2017年9月25日 下午9:01:20
 * 类说明
 */
public class MyFunctionMethod {
    
    // 如果不传入String，则必须要在方法里自己获得
    public static int change(Function<String, Integer> func) {
        String aa = "123";
        return func.apply(aa);
    }
    
    public static void main(String[] args) {
        System.out.println(change(str -> Integer.parseInt(str)));
    }
}
