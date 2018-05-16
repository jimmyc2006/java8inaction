package shuwei.improve.java8.inaction.c10optional;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shuwei
 * @version 创建时间：2017年12月29日 上午9:39:00
 * 类说明
 */
public class MapUse {
    public static void main(String[] args) {
    }
}

class User {
    private long id;
    private String name;
    
    public User(long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}