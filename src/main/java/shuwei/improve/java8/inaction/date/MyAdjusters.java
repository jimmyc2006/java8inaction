package shuwei.improve.java8.inaction.date;

import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjuster;

public class MyAdjusters {
    
    public static TemporalAdjuster firstDayOfWeek() {
        return (temporal) -> temporal.with(ChronoField.DAY_OF_WEEK, temporal.range(ChronoField.DAY_OF_WEEK).getMinimum());
    }
}
