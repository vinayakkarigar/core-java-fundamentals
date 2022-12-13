package com.modern.refresh.again;

public class WordCounter {
    private final int counter;
    private final boolean lastspace;

    public WordCounter(int counter, boolean lastspace) {
        this.counter = counter;
        this.lastspace = lastspace;
    }

    public WordCounter accumulate(Character c) {
        if (Character.isWhitespace(c)) {
            return lastspace ? this : new WordCounter(counter, true);
        }
        else
            return lastspace ? new WordCounter(counter + 1, false) : this;
    }

    public WordCounter combine(WordCounter wordCounter) {
        return new WordCounter(counter + wordCounter.counter, wordCounter.lastspace);
    }

    public int getCounter() {
        return counter;
    }
}
