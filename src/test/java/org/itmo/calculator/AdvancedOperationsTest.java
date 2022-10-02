package org.itmo.calculator;

import org.apache.commons.lang3.tuple.Pair;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

public class AdvancedOperationsTest {
    @ParameterizedTest
    @MethodSource("org.itmo.calculator.AdvancedOperationsTest#doublePlusTestData")
    void doublePlusCalculatesCorrectly(Pair<Integer, Integer> testData) {
        var calculator = new SpecialCalculator(0);
        var expression = testData.getLeft() + " ++ " + testData.getRight();
        var expected = (long) testData.getLeft() + (long) testData.getRight() + (long) testData.getRight();

        assertEquals(
                expected + "",
                calculator.calculate(expression),
                "Выражение: " + expression
        );
    }

    @ParameterizedTest
    @MethodSource("org.itmo.calculator.AdvancedOperationsTest#doubleMinusTestData")
    void doubleMinusCalculatesCorrectly(Pair<Integer, Integer> testData) {
        var calculator = new SpecialCalculator(0);
        var expression = testData.getLeft() + " -- " + testData.getRight();
        var expected = (long) testData.getLeft() - (long) testData.getRight() - (long) testData.getRight();

        assertEquals(
                expected + "",
                calculator.calculate(expression),
                "Выражение: " + expression
        );
    }

    @ParameterizedTest
    @MethodSource("org.itmo.calculator.AdvancedOperationsTest#multiplyDivTestData")
    void multiplyDivCalculatesCorrectly(Pair<Integer, Integer> testData) {
        var calculator = new SpecialCalculator(0);
        var expression = testData.getLeft() + " */ " + testData.getRight();

        String expected;
        if (testData.getRight() == 0) {
            expected = "ERROR_0";
        } else {
            expected = (long) testData.getLeft() * (long) testData.getRight() / (long) testData.getRight() + "";
        }

        assertEquals(
                expected + "",
                calculator.calculate(expression),
                "Выражение: " + expression
        );
    }

    static List<Pair<Integer, Integer>> doublePlusTestData() {
        return List.of(
                Pair.of(1, 1),
                Pair.of(0, 0),
                Pair.of(-55, 97),
                Pair.of(-13, -7),
                Pair.of(256, 256),
                Pair.of(2000000000, 2000000000),
                Pair.of(-2000000000, -2000000000)
        );
    }

    static List<Pair<Integer, Integer>> doubleMinusTestData() {
        return List.of(
                Pair.of(1, 1),
                Pair.of(0, 0),
                Pair.of(-55, 97),
                Pair.of(-13, -7),
                Pair.of(256, 256),
                Pair.of(2000000000, -2000000000),
                Pair.of(-2000000000, 2000000000)
        );
    }

    static List<Pair<Integer, Integer>> multiplyDivTestData() {
        return List.of(
                Pair.of(1, 1),
                Pair.of(0, 0),
                Pair.of(-55, 97),
                Pair.of(-13, -7),
                Pair.of(256, 256),
                Pair.of(2000000000, 2),
                Pair.of(-2000000000, 2)
        );
    }
}
