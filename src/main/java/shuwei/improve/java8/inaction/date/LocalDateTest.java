package shuwei.improve.java8.inaction.date;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class LocalDateTest {
    public static void test1() {
        LocalDate date = LocalDate.of(2018, 3, 12);
        System.out.println("getYear:" + date.getYear());
        System.out.println("getMonth:" + date.getMonth());
        System.out.println("getDayOfMonth:" + date.getDayOfMonth());
        System.out.println("getDayOfWeek:" + date.getDayOfWeek());
        System.out.println("lengthOfMonth:" + date.lengthOfMonth());
        System.out.println("isLeapYear:" + date.isLeapYear());
    }
    
    public static void test2() {
        LocalDate date = LocalDate.now();
        System.out.println("year:" + date.get(ChronoField.YEAR));
        System.out.println("Month:" + date.get(ChronoField.MONTH_OF_YEAR));
        System.out.println("DAY_OF_MONTH:" + date.get(ChronoField.DAY_OF_MONTH));
    }
    
    // 对日期的改变
    public static void test3() {
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        LocalDate date2 = date1.withYear(2011);
        LocalDate date3 = date2.withDayOfMonth(25);
        LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 9);
        
    }
    // 修改对象的属性2
    public static void test4() {
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        LocalDate date2 = date1.plusWeeks(1);
        LocalDate date3 = date2.minusYears(3);
        LocalDate date4 = date3.plus(6, ChronoUnit.MONTHS);
    }
    
    public static void main(String[] args) {
        test2();
        
    }
}
