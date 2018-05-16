package shuwei.improve.java8.inaction.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author shuwei
 * @version 创建时间：2017年9月25日 下午1:49:29
 * 类说明
 */
public class StreamEmpty {
    
    @Test
    public void testStreamWhenEmpty() {
        List<String> testData = new ArrayList<>();
        Assert.assertEquals("", testData.stream().collect(Collectors.joining(",")));
    }
    
    @Test
    public void testStream() {
        List<String> testData = Arrays.asList("aaa", "bbb", "ccc");
        Assert.assertEquals("aaa,bbb,ccc", testData.stream().collect(Collectors.joining(",")));
    }
}
