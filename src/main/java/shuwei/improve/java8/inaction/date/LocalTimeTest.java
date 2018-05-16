package shuwei.improve.java8.inaction.date;

import java.time.LocalTime;

public class LocalTimeTest {
    
    public static void test1() {
        LocalTime time = LocalTime.of(13, 45, 20);
        int hour = time.getHour();
        System.out.println("hour:" + hour);
        int minute = time.getMinute();
        System.out.println("minute:" + minute);
        int second = time.getSecond();
        System.out.println("second:" + second);
    }
    
    public static void test2() {
        
    }
    
    public static void main(String[] args) {
        test1();
    }
}
