package shuwei.improve.java8.inaction.c5.s5;
/**
 * @author shuwei
 * @version 创建时间：2017年12月15日 下午5:54:25
 * 类说明
 */
public class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;
    
    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return trader;
    }

    public int getYear() {
        return year;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Transaction [trader=" + trader + ", year=" + year + ", value=" + value + "]";
    }
}
