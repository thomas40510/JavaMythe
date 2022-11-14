package td3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import td3.Expressions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ExpressionsTest {

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    @ParameterizedTest
    @CsvSource({
            "1, 5",
            "2, 10",
            "3, 2",
            "4, 3",
    })
    @DisplayName("Constants")
    public void testConstant(int value, int tested) {
        Expression e = new Constant(value);
        assertEquals(value, e.value(tested));
    }

    @ParameterizedTest
    @CsvSource({
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
    })
    @DisplayName("Variables")
    public void testVariable(int value) {
        Variable v = new Variable();
        assertEquals(value, v.value(value));
    }

    @ParameterizedTest
    @CsvSource({
            "1, 5, 6, 5",
            "2, 10, 12, 31",
            "3, 2, 5, 2",
            "4, 3, 7, -5",
    })
    @DisplayName("Addition")
    public void testAddition(int a, int b, int expected, int other) {
        Expression e = new Addition(
                new Constant(a),
                new Constant(b)
        );
        assertEquals(expected, e.value(other));

        Expression e2 = new Addition(
                new Constant(a),
                new Variable()
        );
        assertEquals(expected, e2.value(b));
    }

    @ParameterizedTest
    @CsvSource({
            "1.0, 5.0, -4.0, 7.0",
            "2.0, 10.0, -8.0, 31.0",
            "2.5, 2.5, 0.0, 0.5",
            "4.2, 3.1, 1.1, -5.0",
            "4.2, 3.1, 1.1, 0.0",
    })
    @DisplayName("Subtraction")
    public void testSubtraction(double a, double b, double expected, double other) {
        Expression e = new Subtraction(
                new Constant(a),
                new Constant(b)
        );
        assertEquals(expected, e.value(other));

        Expression e2 = new Subtraction(
                new Constant(a),
                new Variable()
        );
        assertEquals(expected, e2.value(b));
        assertNotEquals(expected, e2.value(other));
    }

    @ParameterizedTest
    @CsvSource({
            "1.5, 5.0, 7.5, 7.0",
            "2.0, 10.0, 20.0, 31.0",
            "2.3, 2.5, 5.75, 0.5",
            "4.2, 3.1, 13.02, -5.0",
            "4.2, 3.1, 13.02, 0.0",
            "-3.0, 2.0, -6.0, 0.0",
            "-3.0, -2.5, 7.5, 0.0",
    })
    @DisplayName("Multiplication")
    public void testMultiplication(double a, double b, double expected, double other) {
        Expression e = new Multiplication(
                new Constant(a),
                new Constant(b)
        );
        assertEquals(expected, round(e.value(other), 4));

        Expression e2 = new Multiplication(
                new Constant(a),
                new Variable()
        );
        assertEquals(expected, round(e2.value(b), 4));
        assertNotEquals(expected, round(e2.value(other), 4));
    }

    @Test
    @DisplayName("Complex Operation")
    public void testComplexOperation() {
        Expression e = new Addition(
                new Constant(3.0),
                new Multiplication(
                        new Variable(),
                        new Constant(4.0)
                )
        );
        assertEquals(3.0, e.value(0.0));
        assertEquals(7.0, e.value(1.0));
        assertEquals(11.0, e.value(2.0));
    }

    @ParameterizedTest
    @CsvSource({
           "0.0, 5.3", "4.0, 4.2", "60.5, 0.35", "93.2, 4.61", "-16.74, 0.0",
    })
    @DisplayName("Sine")
    public void testSin(double a, double b) {
        Expression e = new Sin(
                new Constant(a)
        );
        assertEquals(Math.sin(a), e.value(b));

        Expression e2 = new Sin(
                new Variable()
        );
        assertEquals(Math.sin(a), e2.value(a));
    }

    @ParameterizedTest
    @CsvSource({
            "0.0, 5.3", "4.0, 4.2", "60.5, 0.35", "93.2, 4.61", "-16.74, 0.0",
    })
    @DisplayName("Cosine")
    public void testCos(double a, double b) {
        Expression e = new Cos(
                new Constant(a)
        );
        assertEquals(Math.cos(a), e.value(b));

        Expression e2 = new Cos(
                new Variable()
        );
        assertEquals(Math.cos(a), e2.value(a));
    }

    @Test
    @DisplayName("Logarithm")
    public void testLog() {
        Expression e = new Log(
                new Constant(3.0)
        );
        assertEquals(Math.log(3.0), e.value(0.0));
        assertEquals(Math.log(3.0), e.value(1.0));
        assertEquals(Math.log(3.0), e.value(2.0));
    }

    @Test
    @DisplayName("Exponential")
    public void testExp() {
        Expression e = new Exp(
                new Constant(3.0)
        );
        assertEquals(Math.exp(3.0), e.value(0.0));
        assertEquals(Math.exp(3.0), e.value(1.0));
        assertEquals(Math.exp(3.0), e.value(2.0));
    }

    @Test
    @DisplayName("Complex Operation w/ Functions")
    public void testComplexOperationWithFunctions() {
        Expression e = new Addition(
                new Constant(3.0),
                new Multiplication(
                        new Sin(
                                new Variable()
                        ),
                        new Constant(4.0)
                )
        );
        assertEquals(3.0, e.value(0.0));
        assertEquals(3.0 + 4.0 * Math.sin(1.0), e.value(1.0));
        assertEquals(3.0 + 4.0 * Math.sin(2.0), e.value(2.0));
    }

}