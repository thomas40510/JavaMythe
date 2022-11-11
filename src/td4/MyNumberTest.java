package td4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MyNumberTest {
    MyNumber a = new MyNumber(5);
    MyNumber b = new MyNumber(3);

    @Test
    void getNumber() {
        assertEquals(5, a.getNumber());
        assertEquals(3, b.getNumber());
    }

    @Test
    void setNumber() {
        a.setNumber(10);
        assertEquals(10, a.getNumber());

        b.setNumber(20);
        assertEquals(20, b.getNumber());
    }

    @Test
    void add() {
        MyNumber c = a.add(b);
        assertEquals(8, c.getNumber());
    }

    @Test
    void sub() {
        MyNumber c = a.sub(b);
        assertEquals(2, c.getNumber());
    }

    @Test
    void mul() {
        MyNumber c = a.mul(b);
        assertEquals(15, c.getNumber());
    }

    @Test
    void div() {
        MyNumber c = a.div(b);
        assertEquals(1, c.getNumber());
    }

    @Test
    void divByZero() {
        MyNumber c = a.div(new MyNumber(0));
        assertNull(c);
    }
}