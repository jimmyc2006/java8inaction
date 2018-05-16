package shuwei.improve.java8.inaction.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FormatTest {
    
    // 格式化
    public static void test1() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        String s1 = date.format(DateTimeFormatter.BASIC_ISO_DATE);
        String s2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(s1);
        System.out.println(s2);
    }

    // 反解析
    public static void test2() {
        LocalDate date1 = LocalDate.parse("20140318", DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate date2 = LocalDate.parse("2014-03-18", DateTimeFormatter.ISO_LOCAL_DATE);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date11 = LocalDate.of(2014, 3, 18);
        String formattedDate = date11.format(formatter);
        LocalDate date22 = LocalDate.parse(formattedDate, formatter);
    }
    
    // 创建一个本地化的DateTimeFormatter
    public static void test3() {
        DateTimeFormatter italianFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);
        LocalDate date = LocalDate.of(2014, 3, 18);
        String formattedDate = date.format(italianFormatter);
        System.out.println(formattedDate);
        LocalDate date2 = LocalDate.parse(formattedDate, italianFormatter);
    }

    public static void main(String[] args) {
        // test1();
        test3();
    }
}
