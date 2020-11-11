package com.modern.refresh;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class SpliteratorDemo {
    public static class WordCountSpliterator implements Spliterator<Character> {
        private final String string;
        private int currentChar = 0;

        public WordCountSpliterator(String string) {
            this.string = string;
        }

        @Override
        public boolean tryAdvance(Consumer<? super Character> action) {
            action.accept(string.charAt(currentChar++));
            return currentChar < string.length();
        }

        public Spliterator<Character> trySplit() {
            int currentSize = string.length() - currentChar;
            if (currentSize <= 10) {
                return null;
            }

            for (int splitPos = currentChar + currentSize / 2; splitPos < string.length(); splitPos++) {
                if (Character.isWhitespace(string.charAt(splitPos))) {
                    final WordCountSpliterator spliterator = new WordCountSpliterator(string.substring(currentChar, splitPos));
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
            return ORDERED + SIZED + SUBSIZED + CONCURRENT + IMMUTABLE +NONNULL;
        }


    }


    public static class WordCounter {
        private int count;
        private boolean isLastSpace;

        public int getCount() {
            return count;
        }

        public boolean isLastSpace() {
            return isLastSpace;
        }

        public WordCounter(int count, boolean isLastSpace) {
            this.count = count;
            this.isLastSpace = isLastSpace;
        }

        public WordCounter accumulate(Character c) {
            if (Character.isWhitespace(c)) {
                return isLastSpace ? this : new WordCounter(count, true);
            }else {
                return isLastSpace ? new WordCounter(count+1, false): this;
            }
        }

        public WordCounter combine(WordCounter wordCounter) {
            return new WordCounter(count + wordCounter.count, wordCounter.isLastSpace);
        }

    }

    public static int countWordsIteratively(String s) {
        int count = 0;
        boolean lastSpace = true;

        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            }else {
                if (lastSpace) count++;
                lastSpace = false;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String s = " How are you  ? whats up";
        System.out.println("Interative way" +countWordsIteratively(s));

        Stream<Character> characterStream = IntStream.range(0, s.length())
                .mapToObj(s::charAt);
        final WordCounter wordCounter = characterStream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
        System.out.println("Functional way::"+wordCounter.getCount());

        System.out.println("Try Parallel");
        characterStream = IntStream.range(0, s.length())
                .mapToObj(s::charAt);
        final WordCountSpliterator wordCountSpliterator = new WordCountSpliterator(s);
        final Stream<Character> stream = StreamSupport.stream(wordCountSpliterator, true);
        final WordCounter wordCounter1 = stream.parallel()
                .reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
        System.out.println("Parallel way::"+wordCounter1.getCount());


    }

}
