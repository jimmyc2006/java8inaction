package shuwei.improve.java8.inaction.date;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class TemporalAdjusterTest {
    public static void test1() {
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        LocalDate date2 = date1.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        LocalDate date3 = date2.with(TemporalAdjusters.lastDayOfMonth());
        LocalDate date = LocalDate.now();
    }

    public static void test2() {
        LocalDate now = LocalDate.now();
        now = now.plus(3, ChronoUnit.DAYS);
        System.out.println(now.getYear() + "-" + now.getMonthValue() + "-" + now.getDayOfMonth());
        LocalDate firstDayOfWeek = now.with(MyAdjusters.firstDayOfWeek());
        System.out.println(
                firstDayOfWeek.getYear() + "-" + firstDayOfWeek.getMonthValue() + "-" + firstDayOfWeek.getDayOfMonth());
    }

    public static void test3() {
        LocalDate firstMondayLocalDate = LocalDate.now().with(DayOfWeek.MONDAY);
        System.out.println(firstMondayLocalDate.getYear() + "-" + firstMondayLocalDate.getMonthValue() + "-"
                + firstMondayLocalDate.getDayOfMonth());
        System.out.println(firstMondayLocalDate.toEpochDay());
        LocalDateTime t = firstMondayLocalDate.atTime(LocalTime.ofSecondOfDay(0));
        System.out.println(t.toEpochSecond(ZoneOffset.ofHours(0)));
    }

    public static void test4() {
        System.out.println(
                LocalDate.now().with(DayOfWeek.MONDAY).atStartOfDay().toEpochSecond(ZoneOffset.ofTotalSeconds(0)));
        System.out.println(Instant.now().getEpochSecond());
    }

    public static void main(String[] args) {
        test4();
    }
}
