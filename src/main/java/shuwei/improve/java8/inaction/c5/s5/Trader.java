package shuwei.improve.java8.inaction.c5.s5;
/**
 * @author shuwei
 * @version 创建时间：2017年12月15日 下午5:47:54
 * 类说明
 */
public class Trader {
    private final String name;
    private final String city;
    
    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Trader [name=" + name + ", city=" + city + "]";
    }
}
