package org.siit.homework.week6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void testComputeString_whenOutputIsCm_thenResultIsTrue() {
        String input = "15 cm + 8 m = 815 cm";
        calculator.computeString(input);
        assertEquals(calculator.getResultType(), "cm");
    }

    @Test
    public void testComputeString_whenOutputIsDm_thenResultIsTrue() {
        String input = "28 m - 15 dm + 2 dm + 1 m = 277 dm";
        calculator.computeString(input);
        assertEquals(calculator.getResultType(), "dm");
    }

    @ParameterizedTest
    @ValueSource(strings =
            {
                    "10 cm * 1 m - 10 mm = 1090 mm",
                    "15 cm / 8 m * 815 cm = 1630 cm",
                    "1000 m / 3 km = 4000 m",
            })
    public void testComputeString_whenInputIsInvalid_thenResultIsTrue(String input) {
        calculator.computeString(input);
        assertEquals(calculator.getResultType(), "null");
    }

}