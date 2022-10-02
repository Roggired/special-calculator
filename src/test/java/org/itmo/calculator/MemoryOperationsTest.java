package org.itmo.calculator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MemoryOperationsTest {
    @Test
    void invalidConstructorParam1() {
        var calculator = new SpecialCalculator(-4);
        var expected = "ERROR_1";

        assertEquals(
                expected,
                calculator.putLastResultInMemory(0),
                "Параметр конструктора '-4'. Попытка положить результат в ячейку M0"
        );
    }

    @Test
    void invalidConstructorParam2() {
        var calculator = new SpecialCalculator(-24);
        var expected = "ERROR_1";

        assertEquals(
                expected,
                calculator.resetMemory(5),
                "Параметр конструктора '-24'. Попытка очистиить ячейку M5"
        );
    }

    @Test
    void invalidConstructorParam3() {
        var calculator = new SpecialCalculator(101);
        var expected = "ERROR_1";

        assertEquals(
                expected,
                calculator.resetMemory(0),
                "Параметр конструктора '101'. Попытка очистиить ячейку M0"
        );
    }

    @Test
    void zeroConstructorParam1() {
        var calculator = new SpecialCalculator(0);
        var expected = "ERROR_1";

        assertEquals(
                expected,
                calculator.putLastResultInMemory(0),
                "Параметр конструктора '0'. Попытка положить результат в ячейку M0"
        );
    }

    @Test
    void zeroConstructorParam2() {
        var calculator = new SpecialCalculator(0);
        var expected = "ERROR_1";

        assertEquals(
                expected,
                calculator.resetMemory(0),
                "Параметр конструктора '0'. Попытка очистить ячейку M0"
        );
    }

    @Test
    void putInMemoryBeforeCalculations() {
        var calculator = new SpecialCalculator(1);
        var expected = "0";

        assertEquals(
                expected,
                calculator.putLastResultInMemory(0),
                "putLastResultInMemory(0) до вызова calculate"
        );
    }

    @Test
    void putInMemoryAfterCalculations() {
        var calculator = new SpecialCalculator(1);
        calculator.calculate("5 + 2");
        var expected = "7";

        assertEquals(
                expected,
                calculator.putLastResultInMemory(0),
                "putLastResultInMemory(0) после вызова calculate('5 + 2')"
        );
    }

    @Test
    void putInMemoryWhichDoesNotExist() {
        var calculator = new SpecialCalculator(1);
        assertEquals(
                "ERROR_1",
                calculator.putLastResultInMemory(1),
                "Параметр конструктора `1`. putLastResultInMemory(1) до вызова calculate"
        );
    }

    @Test
    void resetMemoryBeforeCalculations() {
        var calculator = new SpecialCalculator(1);
        var expected = "0";

        assertEquals(
                expected,
                calculator.resetMemory(0),
                "resetMemory(0) до вызова calculate"
        );
    }

    @Test
    void resetMemoryAfterCalculations() {
        var calculator = new SpecialCalculator(1);
        calculator.calculate("5 -- 2");
        var expected = "0";

        assertEquals(
                expected,
                calculator.resetMemory(0),
                "resetMemory(0) после вызова calculate('5 -- 2')"
        );
    }

    @Test
    void resetMemoryWhichDoestNotExist() {
        var calculator = new SpecialCalculator(1);
        assertEquals(
                "ERROR_1",
                calculator.resetMemory(1),
                "Параметр конструктора '1'. resetMemory(1) до вызова calculate"
        );
    }

    @Test
    void resetMemoryAfterPutInMemory() {
        var calculator = new SpecialCalculator(1);
        calculator.calculate("5 */ 7");

        assertEquals(
                "5",
                calculator.putLastResultInMemory(0),
                "putLastResultInMemory(0) после вызова calculate('5 */ 7')"
        );

        assertEquals(
                "0",
                calculator.resetMemory(0),
                "resetMemory(0) после вызова putLastResultInMemory(0)"
        );
    }

    @Test
    void operationsWithMi() {
        var calculator = new SpecialCalculator(2);

        assertEquals(
                "1",
                calculator.calculate("5 / 4"),
                "Выражение: 2 / 4"
        );

        assertEquals(
                "1",
                calculator.putLastResultInMemory(0),
                "Параметр конструктора '2'. putLastResultInMemory(0) после вызова calculate('5 / 4')"
        );

        assertEquals(
                "7",
                calculator.calculate("9 -- 1"),
                "Выражение: 9 -- 1"
        );

        assertEquals(
                "7",
                calculator.putLastResultInMemory(1),
                "Параметр конструктора '2'. putLastResultInMemory(1) после вызова calculate('9 -- 1')"
        );

        assertEquals(
                "1",
                calculator.calculate("M0 ^ M1"),
                "Выражение: M0 ^ M1. В ячейке M0 должно быть 1. В ячейке M1 должны быть 7. Параметр конструктора '2'."
        );
    }

    @Test
    void testWith100memoryCells() {
        var calculator = new SpecialCalculator(100);
        long[] expectedResults = new long[100];
        for (int i = 0; i < 100; i++) {
            var randomA = new Random().nextInt(2000000000);
            var randomB = new Random().nextInt(2000000000);
            var expectedResult = (long) randomA + (long) randomB;
            expectedResults[i] = expectedResult;

            assertEquals(
                    expectedResult + "",
                    calculator.calculate(randomA + " + " + randomB),
                    "Выражение: " +  randomA + " + " + randomB
            );

            assertEquals(
                    expectedResult + "",
                    calculator.putLastResultInMemory(i),
                    "Параметр конструктора '100'. putLastResultInMemory(" + i + ") после вызова calculate('" + randomA + " + " + randomB + ")"
            );
        }

        for (int i = 0; i <= 95; i++) {
            String expression = "";
            for (int j = i; j < i + 5; j++) {
                if (j != i + 4) {
                    expression += "M" + j + " + ";
                } else {
                    expression += "M" + j;
                }
            }

            assertEquals(
                    expectedResults[i] + expectedResults[i + 1] + expectedResults[i + 2] + expectedResults[i + 3] + expectedResults[i + 4] + "",
                    calculator.calculate(expression),
                    "После заполнения 100 ячеек памяти. Выражение: " + expression
            );
        }
    }
}
