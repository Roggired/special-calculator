package org.itmo.calculator;

import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ComplexExpressionsCalculator {
    private static final Map<String, BiFunction<Long, Long, Long>> OP_MAP = new HashMap<>() {{
        put("+", Long::sum);
        put("-", (a, b) -> a - b);
        put("*", (a, b) -> a * b);
        put("/", (a, b) -> a / b);
        put("%", (a, b) -> a % b);
        put("^", (a, b) -> a ^ b);
        put("++", (a, b) -> a + b + b);
        put("--", (a, b) -> a - b - b);
        put("*/", (a, b) -> a);
    }};

    private static final Map<String, Function<Long, String>> VALIDATORS_MAP = new HashMap<>() {{
        put("/", (b) -> b == 0 ? "ERROR_0" : "");
        put("*/", (b) -> b == 0 ? "ERROR_0" : "");
        put("%", (b) -> b == 0 ? "ERROR_0" : b < 0 ? "ERROR_3" : "");
        put("^", (b) -> b < 0 ? "ERROR_2" : "");
    }};

    public static String calculate(List<Integer> operands, List<String> ops) {
        if (operands.size() - 1 != ops.size() || ops.size() > 5) {
            throw new RuntimeException("Test data error. Contact authors. Operands: " + operands.size() + " ops: " + ops.size());
        }

        long result = 0;
        long firstOperand = operands.get(0);
        long secondsOperand = operands.get(1);
        for (int i = 0; i < ops.size(); ) {
            String op = ops.get(i);
            var validator = VALIDATORS_MAP.get(op);

            if (validator != null && !validator.apply(secondsOperand).equals("")) {
                return validator.apply(secondsOperand);
            }

            result = OP_MAP.get(op).apply(firstOperand, secondsOperand);
            firstOperand = result;

            i++;

            if (i <= ops.size() - 1) {
                secondsOperand = operands.get(i + 1);
            }
        }

        return result + "";
    }
}
