package shuwei.improve.java8.inaction.c11;

import shuwei.improve.java8.inaction.c11.code.Util;

public class MockExchangeService {
    
    public static double getPrice() {
        Util.delay();
        return 2.2;
    }
    
    public static double getRate() {
        Util.delay();
        return 1.1;
    }
}
