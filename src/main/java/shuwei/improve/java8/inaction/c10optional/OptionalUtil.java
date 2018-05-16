package shuwei.improve.java8.inaction.c10optional;

import java.util.Optional;

/**
 * @author shuwei
 * @version 创建时间：2017年9月26日 上午9:05:49 类说明
 */
public class OptionalUtil {
    // 这个可以作为一个工具类
    public static Optional<Integer> stringToInt(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
