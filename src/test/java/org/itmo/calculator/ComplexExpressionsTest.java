package org.itmo.calculator;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ComplexExpressionsTest {
    @ParameterizedTest
    @MethodSource("org.itmo.calculator.ComplexExpressionsTest#complexExpressionTestData")
    void complexExpressionWithoutMi(Pair<List<Integer>, List<String>> testData) {
        var operands = testData.getLeft();
        var ops = testData.getRight();

        StringBuilder expression = new StringBuilder(operands.get(0) + "");
        int operandIndex = 1;
        for (String op : ops) {
            expression.append(" ");
            expression.append(op);
            expression.append(" ");
            expression.append(operands.get(operandIndex++));
        }

        var expected = ComplexExpressionsCalculator.calculate(operands,ops);

        assertEquals(
                expected,
                new SpecialCalculator(0).calculate(expression.toString()),
                "Выражение: " + expression
        );
    }

    @Test
    void complexExpressionWithMi() {
        var calculator = new SpecialCalculator(1);
        var expression1 = "4 * 6 % 5";
        var expected1 = "4";
        var expression2 = "6 + M0";
        var expected2 = "10";

        assertEquals(
                expected1,
                calculator.calculate(expression1),
                "Выражение: " + expression1
        );

        assertEquals(
                expected1,
                calculator.putLastResultInMemory(0),
                "Параметр конструктора '1'. putLastResultInMemory(0) после вызова calculate('" + expression1 + "')"
        );

        assertEquals(
                expected2,
                calculator.calculate(expression2),
                "Выражение: " + expression2
        );
    }

    static List<Pair<List<Integer>, List<String>>> complexExpressionTestData() {
        return List.of(
                Pair.of(
                        List.of(1, 2, 3, 4, 5, 6),
                        List.of("+", "+", "+", "+", "+")
                ),
                Pair.of(
                        List.of(1, 2, 3, 4, 5, 6),
                        List.of("+", "-", "+", "-", "+")
                ),
                Pair.of(
                        List.of(1, 2, 0),
                        List.of("+", "/")
                ),
                Pair.of(
                        List.of(2, 2, 1),
                        List.of("-", "/")
                ),
                Pair.of(
                        List.of(2, 2, -1),
                        List.of("-", "%")
                ),
                Pair.of(
                        List.of(2, 2, -1),
                        List.of("+", "^")
                )
        );
    }
}
