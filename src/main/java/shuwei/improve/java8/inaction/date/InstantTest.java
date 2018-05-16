package shuwei.improve.java8.inaction.date;

import java.time.Instant;
import java.time.temporal.ChronoField;

public class InstantTest {
    public static void test1() {
        Instant t1 = Instant.ofEpochSecond(3);
        Instant t2 = Instant.ofEpochSecond(3, 0);
        Instant t3 = Instant.ofEpochSecond(2, 1_000_000_000);
        Instant t4 = Instant.ofEpochSecond(4, -1_000_000_000);
    }
    
    public static void test2() {
        Instant t = Instant.now();
        int day = t.get(ChronoField.DAY_OF_MONTH);
        System.out.println("day:" + day);
    }
    
    public static void main(String[] args) {
        test2();
    }
}
