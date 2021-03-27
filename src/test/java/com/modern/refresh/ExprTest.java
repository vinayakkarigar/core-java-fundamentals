package com.modern.refresh;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Setup;

import static org.junit.jupiter.api.Assertions.*;

class ExprTest {

    @BeforeEach
    void setup() {
        System.out.println("I am here to setup everything");
    }

    @Test
    void testHelloWorld() {
        System.out.println("I would be testing everything here");
    }

}