package shuwei.improve.java8.inaction.c7;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WordCounterSpliterator implements Spliterator<Character> {

    private final String string;
    private int currentChar = 0;

    public WordCounterSpliterator(String string) {
        this.string = string;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(string.charAt(currentChar++));
        return currentChar < string.length();
    }

    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentChar;
        if (currentSize < 10) {
            return null;
        }
        for (int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++) {
            if (Character.isWhitespace(string.charAt(splitPos))) {
                Spliterator<Character> spliterator = new WordCounterSpliterator(string.substring(currentChar, splitPos));
                currentChar = splitPos;
                return spliterator;
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }

    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
    
    public static int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if(lastSpace) {
                    counter++;
                }
                lastSpace = false;
            }
        }
        return counter;
    }
    
    // 改为函数式编程
    public static void main(String[] args) {
        final String SENTENCE = "Nel mezzo  del  cammin  di  nostra  vita  " +
                "mi  ritrovai  in  una  selva  oscura" + 
                " che la dritta via era smarrita";
        System.out.println("Found " + countWordsIteratively(SENTENCE) + " words");
        Stream<Character> stream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
        
    }
}
