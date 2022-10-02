package org.itmo.calculator;

import org.apache.commons.lang3.tuple.Pair;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

public class SimpleOperationsTest {
    @ParameterizedTest
    @MethodSource("org.itmo.calculator.SimpleOperationsTest#plusTestData")
    void plusCalculatesCorrectly(Pair<Integer, Integer> testData) {
        var calculator = new SpecialCalculator(0);
        var expression = testData.getLeft() + " + " + testData.getRight();
        var expected = (long) testData.getLeft() + (long) testData.getRight();
        assertEquals(
                expected + "",
                calculator.calculate(expression),
                "Выражение: " + expression
        );
    }

    @ParameterizedTest
    @MethodSource("org.itmo.calculator.SimpleOperationsTest#minusTestData")
    void minusCalculatesCorrectly(Pair<Integer, Integer> testData) {
        var calculator = new SpecialCalculator(0);
        var expression = testData.getLeft() + " - " + testData.getRight();
        var expected = (long) testData.getLeft() - (long) testData.getRight();
        assertEquals(
                expected + "",
                calculator.calculate(expression),
                "Выражение: " + expression
        );
    }

    @ParameterizedTest
    @MethodSource("org.itmo.calculator.SimpleOperationsTest#multiplyTestData")
    void multiplyCalculatesCorrectly(Pair<Integer, Integer> testData) {
        var calculator = new SpecialCalculator(0);
        var expression = testData.getLeft() + " * " + testData.getRight();
        var expected = (long) testData.getLeft() * (long) testData.getRight();
        assertEquals(
                expected + "",
                calculator.calculate(expression),
                "Выражение: " + expression
        );
    }

    @ParameterizedTest
    @MethodSource("org.itmo.calculator.SimpleOperationsTest#divTestData")
    void divCalculatesCorrectly(Pair<Integer, Integer> testData) {
        var calculator = new SpecialCalculator(0);
        var expression = testData.getLeft() + " / " + testData.getRight();

        String expected;
        if (testData.getRight() == 0) {
            expected = "ERROR_0";
        } else {
            expected = (long) testData.getLeft() / (long) testData.getRight() + "";
        }

        assertEquals(
                expected,
                calculator.calculate(expression),
                "Выражение: " + expression
        );
    }

    @ParameterizedTest
    @MethodSource("org.itmo.calculator.SimpleOperationsTest#modTestData")
    void modCalculatesCorrectly(Pair<Integer, Integer> testData) {
        var calculator = new SpecialCalculator(0);
        var expression = testData.getLeft() + " % " + testData.getRight();

        String expected;
        if (testData.getRight() == 0) {
            expected = "ERROR_0";
        } else if (testData.getRight() < 0) {
            expected = "ERROR_3";
        } else {
            expected = (long) testData.getLeft() % (long) testData.getRight() + "";
        }

        assertEquals(
                expected,
                calculator.calculate(expression),
                "Выражение: " + expression
        );
    }

    @ParameterizedTest
    @MethodSource("org.itmo.calculator.SimpleOperationsTest#powerTestData")
    void powerCalculatesCorrectly(Pair<Integer, Integer> testData) {
        var calculator = new SpecialCalculator(0);
        var expression = testData.getLeft() + " ^ " + testData.getRight();

        String expected;
        if (testData.getRight() < 0) {
            expected = "ERROR_2";
        } else {
            long result = 1;
            for (int i = 0; i < testData.getRight(); i++) {
                result *= testData.getLeft();
            }

            expected = result + "";
        }

        assertEquals(
                expected,
                calculator.calculate(expression),
                "Выражение: " + expression
        );
    }

    static List<Pair<Integer, Integer>> plusTestData() {
        return List.of(
                Pair.of(1, 0),
                Pair.of(-55, 97),
                Pair.of(256, 256),
                Pair.of(0, 0),
                Pair.of(2000000000, 2000000000),
                Pair.of(-2000000000, -2000000000)
        );
    }

    static List<Pair<Integer, Integer>> minusTestData() {
        return List.of(
                Pair.of(1, 0),
                Pair.of(-55, 97),
                Pair.of(256, 256),
                Pair.of(0, 0),
                Pair.of(-2000000000, 2000000000),
                Pair.of(2000000000, -2000000000)
        );
    }

    static List<Pair<Integer, Integer>> multiplyTestData() {
        return List.of(
                Pair.of(1, 0),
                Pair.of(-55, 97),
                Pair.of(256, 256),
                Pair.of(0, 0),
                Pair.of(400000000, 10),
                Pair.of(-400000000, 10)
        );
    }

    static List<Pair<Integer, Integer>> divTestData() {
        return List.of(
                Pair.of(1, 5),
                Pair.of(3, -2),
                Pair.of(256, -256),
                Pair.of(10, 2),
                Pair.of(13, 1),
                Pair.of(15, 5),
                Pair.of(1, 0)
        );
    }

    static List<Pair<Integer, Integer>> modTestData() {
        return List.of(
                Pair.of(1, 5),
                Pair.of(3, 2),
                Pair.of(256, 256),
                Pair.of(10, 2),
                Pair.of(13, 1),
                Pair.of(15, 5),
                Pair.of(3, 7),
                Pair.of(1, 0),
                Pair.of(1, -1)
        );
    }

    static List<Pair<Integer, Integer>> powerTestData() {
        return List.of(
                Pair.of(1, 0),
                Pair.of(100, 0),
                Pair.of(250, -1),
                Pair.of(0, -1),
                Pair.of(-5, 2),
                Pair.of(-5, 3),
                Pair.of(2, 40)
        );
    }
}
