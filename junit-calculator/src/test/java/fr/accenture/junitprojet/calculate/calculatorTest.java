package fr.accenture.junitprojet.calculate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test Math operations in calculator class")
class CalculatorTest {
    Calculator calculator;

    @BeforeAll
    static void setup() {
        System.out.println("setup");
    }

    @AfterAll
    static void cleanup() {
        System.out.println("cleanup");
    }

    @BeforeEach
    void beforeCalculate() {
        calculator = new Calculator();
    }

    @DisplayName("division 6/2 = 3")
    @Test
    void test_integer_division_when_six_is_divided_by_two_should_return_tree() {
        Assertions.assertEquals(3, calculator.integerDivision(6, 2));
    }

    @Disabled("TODO: when this method as fix divided by 0")
    @DisplayName("divided = 0")
    @Test
    void test_integer_division_when_zero_is_user_in_divider_should_throw_runtime_exception() {
        Assertions.assertThrows(RuntimeException.class, () -> calculator.integerDivision(3, 0), "the division with O don't work");
    }

    @DisplayName("dividend = 0")
    @Test
    void test_integer_when_zero_is_user_in_dividend_should_return_zero() {
        Assertions.assertEquals(0, calculator.integerDivision(0, 7), "zero can be accepted");
    }

    @DisplayName("subtraction 7-5=2")
    @Test
    void test_integer_subtraction_when_subtracted_two_integer_should_return_integer() {
        Assertions.assertEquals(2, calculator.integerSubtraction(7, 5), "subtraction don't work");
    }

}