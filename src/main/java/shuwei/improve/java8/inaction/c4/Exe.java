package shuwei.improve.java8.inaction.c4;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shuwei
 * @version 创建时间：2017年11月30日 下午5:48:10 类说明
 */
public class Exe {
    public static void main(String[] args) {
        List<Dish> result = Dish.menu.stream().filter(dish -> dish.getType() == Dish.Type.MEAT || dish.getType() == Dish.Type.FISH)
                .limit(2).collect(Collectors.toList());
        System.out.println(result);
    }
}
