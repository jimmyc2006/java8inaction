package shuwei.improve.java8.inaction.c5.s5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

/**
 * @author shuwei
 * @version 创建时间：2017年12月15日 下午5:56:09 类说明
 */
public class ExtList {
    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");

    List<Transaction> transactions = Arrays.asList(new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000), new Transaction(raoul, 2011, 400), new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700), new Transaction(alan, 2012, 950));

    // 找出2011年发生的所有交易，并按交易额排序(从低到高)
    public List<Transaction> one() {
        return transactions.stream().filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());
    }

    // 交易员都在哪些不同的城市工作过?
    public List<String> two() {
        return transactions.stream().map(trac -> trac.getTrader().getCity()).distinct().collect(Collectors.toList());
    }

    // 查找所有来自于剑桥的交易员，并按姓名排序
    public List<Trader> three() {
        return transactions.stream().map(Transaction::getTrader).filter(t -> "Cambridge".equals(t.getCity())).distinct()
                .sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList());
    }

    // 返回所有交易员的姓名字符串，按字母顺序排序
    public String four() {
        return transactions.stream().map(t -> t.getTrader().getName()).distinct().sorted()
                .collect(Collectors.joining(","));
    }
    
    // 有没有交易员是在米兰工作的
    public boolean five() {
        return transactions.stream().anyMatch(t -> "Milan".equals(t.getTrader().getCity()));
        // return transactions.stream().filter(t -> "Milan".equals(t.getTrader().getCity())).findAny().isPresent();
    }
    
    // 打印生活在剑桥的交易员的所有交易额
    public int six() {
        return transactions.stream().filter(t -> "Cambridge".equals(t.getTrader().getCity())).mapToInt(Transaction::getValue).sum();
    }
    
    // 所有交易中，最高的交易额是多少
    public OptionalInt seven() {
        return transactions.stream().mapToInt(Transaction::getValue).max();
    }
    
    // 找到交易额最小的交易
    public Optional<Transaction> eight() {
        return transactions.stream().min((t1,t2) -> Integer.compare(t1.getValue(), t2.getValue()));
    }

    public static void main(String[] args) {
        ExtList el = new ExtList();
        System.out.println("-----------one-------");
        el.one().forEach(System.out::println);
        System.out.println("-----------two-------");
        el.two().forEach(System.out::println);
        System.out.println("-----------three-----");
        el.three().forEach(System.out::println);
        System.out.println("-----------four------");
        System.out.println(el.four());
        System.out.println("-----------five------");
        System.out.println(el.five());
        System.out.println("-----------six------");
        System.out.println(el.six());
        System.out.println("-----------seven------");
        System.out.println(el.seven().getAsInt());
        System.out.println("-----------eight------");
        System.out.println(el.eight());
    }
}
