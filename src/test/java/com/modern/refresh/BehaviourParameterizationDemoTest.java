package com.modern.refresh;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BehaviourParameterizationDemoTest {

    @Test
    void testFilter() {
        final List<Integer> integers = List.of(1, 2, 3, 4, 5, 6, 7, 8);

        final List<Integer> even = BehaviourParameterizationDemo.filter(integers, i -> i % 2 == 0);
        final List<Integer> lessThan3 = BehaviourParameterizationDemo.filter(integers, i -> i < 3);

        assertEquals(List.of(2, 4, 6, 8), even);
        assertEquals(List.of(1, 2), lessThan3);


    }
}