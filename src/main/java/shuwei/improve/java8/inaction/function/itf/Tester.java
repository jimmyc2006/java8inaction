package shuwei.improve.java8.inaction.function.itf;
/**
 * @author shuwei
 * @version 创建时间：2017年11月16日 下午4:31:54
 * 类说明
 */
public class Tester {
    public void test(int a, int b, Adder adder) {
        System.out.println(adder.add(a, b));
    }
    
    public void test2(double a, double b, SmartAdder smartAdder) {
        System.out.println(smartAdder.add(a, b));
    }
    
    public static void main(String[] args) {
        Tester tester = new Tester();
        // tester.test(1, 2, (x,y) -> x + y);
        tester.test2(1.1, 2.2, (double x, double y) -> x + y);
    }
}
