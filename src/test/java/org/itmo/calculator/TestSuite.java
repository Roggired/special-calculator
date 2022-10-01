package org.itmo.calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestSuite {
    @Test
    void baseTest() {
        var calculator = new SpecialCalculator(0);
        var result = calculator.calculate("5 + M0");
        assertEquals("ERROR_1", result, "Выражение: '5 + M0'");
    }
}
