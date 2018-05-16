package shuwei.improve.java8.inaction;

import java.util.function.Predicate;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @author shuwei
 * @version 创建时间：2017年11月30日 上午10:07:37
 * Predicate的使用
 */
public class PredicateTest {
    Predicate<Apple> redApple = a -> "red".equals(a.getColor());
    Predicate<Apple> heavyApple = a -> a.getWeight() > 150;
    Predicate<Apple> greenApple = a -> "green".equals(a.getColor());
    
    Apple redLight = new Apple(150, "red");
    Apple greenHeavy = new Apple(160, "green");
    Apple redHeavy = new Apple(170, "red");
    
    @Test
    public void testNot() {
        Assert.assertTrue(redApple.test(redLight));
        Assert.assertFalse(redApple.test(greenHeavy));
        Assert.assertTrue(redApple.negate().test(greenHeavy));
        Assert.assertTrue(heavyApple.test(redHeavy));
        Assert.assertFalse(heavyApple.test(redLight));
    }
    
    @Test
    public void testAnd() {
        Assert.assertFalse(redApple.and(heavyApple).test(redLight));
        Assert.assertTrue(redApple.and(heavyApple).test(redHeavy));
    }
    
    @Test
    public void testOr() {
        Assert.assertTrue(redApple.or(heavyApple).test(redLight));
        Assert.assertTrue(redApple.or(heavyApple).test(greenHeavy));
    }
    
    @Test
    public void testMul() {
        // this is (red && heavy) || green
        Predicate<Apple> a = redApple.and(heavyApple).or(greenApple);
        Assert.assertFalse(a.test(redLight));
        Assert.assertTrue(a.test(greenHeavy));
        // this is (red || Heavy) && green
        Predicate<Apple> b = redApple.or(heavyApple).and(greenApple);
        Assert.assertFalse(b.test(redLight));
        Assert.assertFalse(b.test(redHeavy));
        Assert.assertTrue(b.test(greenHeavy));
    }
}

class Apple {
    private int weight;
    private String color;
    
    public Apple(int weight, String color) {
        super();
        this.weight = weight;
        this.color = color;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
}