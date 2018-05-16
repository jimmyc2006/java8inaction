package shuwei.improve.java8.inaction.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class LocalDateTimeTest {
    public static void test1() {
        LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);
        LocalDate date = LocalDate.parse("2014-03-18");
        LocalTime time = LocalTime.parse("13:45:20");
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(13, 45, 20);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);
        
        // 提取
        LocalDate date1 = dt1.toLocalDate();
        LocalTime time1 = dt1.toLocalTime();
    }
}
