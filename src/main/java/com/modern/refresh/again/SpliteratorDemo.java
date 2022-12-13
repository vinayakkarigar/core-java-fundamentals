package com.modern.refresh.again;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SpliteratorDemo {

    static final String SENTENCE = " Nel   mezzo del cammin  di nostra  vita " +
            "mi  ritrovai in una  selva oscura" +
            " ché la  dritta via era   smarrita ";

    public static void main(String[] args) {
        SpliteratorDemo demo = new SpliteratorDemo();
        System.out.println(demo.countWordsIteratively(SENTENCE));
        Stream<Character> stream = IntStream.range(0, SENTENCE.length())
                .mapToObj(SENTENCE::charAt);
        stream.forEach(System.out::println);
    }

    public int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if(Character.isWhitespace(c)){
                lastSpace = true;
            }
            else {
                if(lastSpace) counter++;
                lastSpace = false;
            }
        }
        return counter;
    }
}
