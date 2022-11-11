package td3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpressionsTest {

    @Test
    public void testConstant() {
        Expressions.Constant c = new Expressions.Constant(3.0);
        assertEquals(3.0, c.value(0.0));
        assertEquals(3.0, c.value(1.0));
        assertEquals(3.0, c.value(2.0));
    }

    @Test
    public void testVariable() {
        Expressions.Variable v = new Expressions.Variable();
        assertEquals(0.0, v.value(0.0));
        assertEquals(1.0, v.value(1.0));
        assertEquals(2.0, v.value(2.0));
    }

    @Test
    public void testAddition() {
        Expressions.Expression e = new Expressions.Addition(
                new Expressions.Constant(3.0),
                new Expressions.Constant(4.0)
        );
        assertEquals(7.0, e.value(0.0));
        assertEquals(7.0, e.value(1.0));
        assertEquals(7.0, e.value(2.0));
    }

    @Test
    public void testSubtraction() {
        Expressions.Expression e = new Expressions.Subtraction(
                new Expressions.Constant(3.0),
                new Expressions.Constant(4.0)
        );
        assertEquals(-1.0, e.value(0.0));
        assertEquals(-1.0, e.value(1.0));
        assertEquals(-1.0, e.value(2.0));
    }

    @Test
    public void testMultiplication() {
        Expressions.Expression e = new Expressions.Multiplication(
                new Expressions.Constant(3.0),
                new Expressions.Constant(4.0)
        );
        assertEquals(12.0, e.value(0.0));
        assertEquals(12.0, e.value(1.0));
        assertEquals(12.0, e.value(2.0));
    }

    @Test
    public void testComplexOperation() {
        Expressions.Expression e = new Expressions.Addition(
                new Expressions.Constant(3.0),
                new Expressions.Multiplication(
                        new Expressions.Variable(),
                        new Expressions.Constant(4.0)
                )
        );
        assertEquals(3.0, e.value(0.0));
        assertEquals(7.0, e.value(1.0));
        assertEquals(11.0, e.value(2.0));
    }

    @Test
    public void testSin() {
        Expressions.Expression e = new Expressions.Sin(
                new Expressions.Constant(3.0)
        );
        assertEquals(Math.sin(3.0), e.value(0.0));
        assertEquals(Math.sin(3.0), e.value(1.0));
        assertEquals(Math.sin(3.0), e.value(2.0));
    }

    @Test
    public void testCos() {
        Expressions.Expression e = new Expressions.Cos(
                new Expressions.Constant(3.0)
        );
        assertEquals(Math.cos(3.0), e.value(0.0));
        assertEquals(Math.cos(3.0), e.value(1.0));
        assertEquals(Math.cos(3.0), e.value(2.0));
    }

    @Test
    public void testLog() {
        Expressions.Expression e = new Expressions.Log(
                new Expressions.Constant(3.0)
        );
        assertEquals(Math.log(3.0), e.value(0.0));
        assertEquals(Math.log(3.0), e.value(1.0));
        assertEquals(Math.log(3.0), e.value(2.0));
    }

    @Test
    public void testExp() {
        Expressions.Expression e = new Expressions.Exp(
                new Expressions.Constant(3.0)
        );
        assertEquals(Math.exp(3.0), e.value(0.0));
        assertEquals(Math.exp(3.0), e.value(1.0));
        assertEquals(Math.exp(3.0), e.value(2.0));
    }

    @Test
    public void testComplexOperationWithFunctions() {
        Expressions.Expression e = new Expressions.Addition(
                new Expressions.Constant(3.0),
                new Expressions.Multiplication(
                        new Expressions.Sin(
                                new Expressions.Variable()
                        ),
                        new Expressions.Constant(4.0)
                )
        );
        assertEquals(3.0, e.value(0.0));
        assertEquals(3.0 + 4.0 * Math.sin(1.0), e.value(1.0));
        assertEquals(3.0 + 4.0 * Math.sin(2.0), e.value(2.0));
    }

}