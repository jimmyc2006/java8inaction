package shuwei.improve.java8.inaction.c5;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author shuwei
 * @version 创建时间：2017年12月15日 下午5:38:34
 * 类说明
 */
public class ReduceTest {
    // 有初始值，即时没有元素，也可以返回初始值
    public static int simple(List<Integer> numbers) {
        int sum = numbers.stream().reduce(0, Integer::sum);
        return sum;
    }
    
    // 没有初始值，如果集合没数据就会为空，所以返回Optional
    public static Optional<Integer> simpleNotInit(List<Integer> numbers) {
        Optional<Integer> sum = numbers.stream().reduce(Integer::sum);
        return sum;
    }
    
    
    
    public static void main(String[] args) {
        List<Integer> d = Arrays.asList(1, 2, 3);
        System.out.println(simple(d));
    }
}
