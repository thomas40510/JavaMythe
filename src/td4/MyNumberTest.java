package td4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MyNumberTest {
    MyNumber a = new MyNumber(5);
    MyNumber b = new MyNumber(3);

    @Test
    @DisplayName("Getter")
    void getNumber() {
        assertEquals(5, a.getNumber());
        assertEquals(3, b.getNumber());
    }

    @Test
    @DisplayName("Setter")
    void setNumber() {
        a.setNumber(10);
        assertEquals(10, a.getNumber());

        b.setNumber(20);
        assertEquals(20, b.getNumber());
    }

    @ParameterizedTest
    @CsvSource({
            "8, 3, 11",
            "10, 5, 15",
            "20, 10, 30",
            "30, 15, 45",
            "40, 20, 60",
            "50, 25, 75",
            "60, 30, 90",
            "70, 35, 105",
            "80, 40, 120",
            "90, 45, 135",
            "100, 50, 150",
    })
    @DisplayName("Addition")
    void add(int a, int b, int expected) {
        MyNumber a1 = new MyNumber(a);
        MyNumber b1 = new MyNumber(b);
        MyNumber result = a1.add(b1);
        assertEquals(expected, result.getNumber());
    }

    @ParameterizedTest
    @CsvSource({
            "8, 3, 5",
            "10, 5, 5",
            "20, 10, 10",
            "30, 15, 15",
            "40, 20, 20",
            "50, 25, 25",
            "60, 30, 30",
            "70, 35, 35",
            "80, 40, 40",
            "90, 45, 45",
            "100, 50, 50",
            "-10, 5, -15",
    })
    @DisplayName("Subtraction")
    void sub(int a, int b, int expected) {
        MyNumber a1 = new MyNumber(a);
        MyNumber b1 = new MyNumber(b);
        MyNumber result = a1.sub(b1);
        assertEquals(expected, result.getNumber());
    }

    @ParameterizedTest
    @CsvSource({
            "8, 3, 24",
            "10, 5, 50",
            "20, 10, 200",
            "30, 15, 450",
            "40, 20, 800",
            "50, 25, 1250",
            "60, 30, 1800",
            "70, 35, 2450",
            "80, 40, 3200",
            "90, 45, 4050",
            "100, 50, 5000",
    })
    @DisplayName("Multiplication")
    void mul(int a, int b, int expected) {
        MyNumber a1 = new MyNumber(a);
        MyNumber b1 = new MyNumber(b);
        MyNumber result = a1.mul(b1);
        assertEquals(expected, result.getNumber());
    }

    @ParameterizedTest
    @CsvSource({
            "8, 2, 4",
            "10, 2, 5",
            "20, 2, 10",
            "30, 2, 15",
            "40, 2, 20",
            "50, 2, 25",
            "60, 2, 30",
            "70, 2, 35",
            "80, 2, 40",
            "90, 2, 45",
            "100, 2, 50",
    })
    @DisplayName("Division")
    void div(int a, int b, int expected) {
        MyNumber a1 = new MyNumber(a);
        MyNumber b1 = new MyNumber(b);
        MyNumber result = a1.div(b1);
        assertEquals(expected, result.getNumber());
    }

    @Test
    void divByZero() {
        MyNumber c = a.div(new MyNumber(0));
        assertNull(c);
    }
}