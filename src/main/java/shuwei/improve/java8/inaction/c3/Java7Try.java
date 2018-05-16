package shuwei.improve.java8.inaction.c3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author shuwei
 * @version 创建时间：2017年11月16日 下午5:48:01 类说明
 */
public class Java7Try {
    
    public static String read() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return br.readLine();
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        System.out.println(Java7Try.read());
    }
}
