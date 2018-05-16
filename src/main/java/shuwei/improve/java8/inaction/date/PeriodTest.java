package shuwei.improve.java8.inaction.date;

import java.time.LocalDate;
import java.time.Period;

public class PeriodTest {
    public static void test1() {
        Period tenDays = Period.between(LocalDate.of(2014, 3, 8), LocalDate.of(2014, 3, 18));
    }
}
