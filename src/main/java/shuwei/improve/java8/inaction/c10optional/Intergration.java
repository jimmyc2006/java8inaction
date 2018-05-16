package shuwei.improve.java8.inaction.c10optional;

import java.util.Optional;
import java.util.Properties;

/**
 * @author shuwei
 * @version 创建时间：2017年9月26日 上午9:21:05 类说明
 */
public class Intergration {
    public int oldReadDuration(Properties props, String name) {
        String value = props.getProperty(name);
        if (value != null) {
            try {
                int i = Integer.parseInt(value);
                if (i > 0) {
                    return i;
                }
            } catch (NumberFormatException nfe) {
                // pass
            }
        }
        return 0;
    }

    public int newReadDuration(Properties props, String name) {
        return Optional.ofNullable(props.getProperty(name)).flatMap(OptionalUtil::stringToInt).filter(i -> i > 0)
                .orElse(0);
    }
}
