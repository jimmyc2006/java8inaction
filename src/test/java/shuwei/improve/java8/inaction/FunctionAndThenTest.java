package shuwei.improve.java8.inaction;

import java.util.function.Function;
import java.util.function.ToIntFunction;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @author shuwei
 * @version 创建时间：2017年11月30日 上午9:49:07
 * Function的使用
 */
public class FunctionAndThenTest {
    Function<Integer, Integer> fx = x -> x + 1;
    Function<Integer, Integer> gx = x -> x * 2;
    
    @Test
    public void testFunction() {
        ToIntFunction<Integer> test = x -> (x + 1) * 2;
        Assert.assertEquals(6, test.applyAsInt(2));
        Assert.assertEquals(2, test.applyAsInt(0));
    }
    
    @Test
    public void testAndThen() {
        // g(f(x))
        Assert.assertEquals(6, fx.andThen(gx).apply(2).intValue());
        // g(g(x))
        Assert.assertEquals(5, fx.compose(gx).apply(2).intValue());
    }
}
