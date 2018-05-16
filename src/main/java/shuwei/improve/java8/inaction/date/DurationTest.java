package shuwei.improve.java8.inaction.date;

import java.time.Duration;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class DurationTest {

    /**
     * Duration可以从两个LocalDate/LocalTime/Instant中获得
     */
    public static void test1() {
        LocalTime time1 = LocalTime.now();
        LocalTime time2 = LocalTime.now();
        Duration d1 = Duration.between(time1, time2);
    }
    
    public static void test2() {
        Duration threeMinutes = Duration.ofMinutes(3);
        Duration threeMinutes2 = Duration.of(3, ChronoUnit.MINUTES);
        
        Period tenDays = Period.ofDays(10);
        Period threeWeeks = Period.ofWeeks(3);
        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
        
    }
}
