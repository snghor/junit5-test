package fr.accenture.junitprojet.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DemoTest {
    private static final String HELLO_WORLD = "Hello world";

    @Test
    void testHelloWorld(){
        Assertions.assertEquals(11, HELLO_WORLD.length());
    }
}
