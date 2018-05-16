package shuwei.improve.java8.inaction.c5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author shuwei
 * @version 创建时间：2017年12月12日 上午9:12:55
 */
public class Exe5_2 {
    public static List<int[]> findAllCouple(List<Integer> l1, List<Integer> l2) {
        // 用以前的方法就是使用两层循环i -> l2.stream().map(j -> new int[] {i, j})
        return l1.stream().flatMap(i -> l2.stream().<int[]>map(j -> new int[]{i, j})).collect(Collectors.toList());
    }

    public static void test() {
        List<TTT> list = new ArrayList<>();
        list.add(new TTT());
        list.add(new TTT());
        System.out.println(list.stream().flatMap(t -> t.getNames().stream()).collect(Collectors.toList()));
    }

    public static void tt() {
        List<Integer> number1 = Arrays.asList(1, 2, 3);
        List<Integer> number2 = Arrays.asList(3, 4);
        List<int[]> pairs =
                number1.stream()
                        .flatMap(i -> 
                            {
                                Stream<int[]> tmp = number2.stream().map(j -> new int[] {i, j});
                                return tmp;
                            }
                        )
                        .collect(Collectors.toList());
        pairs.stream().forEach(x -> System.out.println(x[0] + ":" + x[1]));
    }

    public static void main(String[] args) {
        // List<Integer> l1 = Arrays.asList(1, 2, 3);
        // List<Integer> l2 = Arrays.asList(3, 4);
        // findAllCouple(l1, l2).stream().forEach(x -> System.out.println());
        tt();
    }
}


class TTT {
    List<String> names;

    public TTT() {
        names = new ArrayList<>();
        names.add("one");
        names.add("two");
        names.add("three");
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}
