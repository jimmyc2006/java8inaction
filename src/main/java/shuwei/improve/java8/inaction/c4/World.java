package shuwei.improve.java8.inaction.c4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author shuwei
 * @version 创建时间：2017年11月30日 下午9:39:56 类说明
 */
public class World {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("Hello", "World");
        List<String> r = words.stream().flatMap(word -> Stream.of(word.split(""))).distinct().collect(Collectors.toList());
        System.out.println(r);
        // "abc".chars().mapToObj(i -> new Character((char) i)).forEach(System.out::print);
         // List<String> words = Arrays.asList("Hello", "World");
//         System.out.println(words.stream().flatMap(str -> str.chars().mapToObj(i ->
//         new Character((char) i)))
//         .distinct().collect(Collectors.toList()));
    }
}
