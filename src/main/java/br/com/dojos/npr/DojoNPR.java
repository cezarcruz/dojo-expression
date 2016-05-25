package br.com.dojos.npr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by cezar on 18/05/16.
 */
public class DojoNPR {

    private static final String OPERATIONS = "+-*/";

    public String solveExpression(final String expression) {
        final String reversedExpression = changeExpression(expression.replace(" ", ""));
        final List<String> expressionList = new ArrayList<>();

        for (char c: reversedExpression.toCharArray()) {
            final String val = String.valueOf(c);

            if (OPERATIONS.contains(val)) {
                final String v1 = expressionList.get(expressionList.size() - 2);
                final String v2 = expressionList.get(expressionList.size() - 1);
                final String result = this.makeMath(v1, v2, val);
                expressionList.remove(expressionList.size() - 1);
                expressionList.remove(expressionList.size() - 1);
                expressionList.add(result);
            } else {
                expressionList.add(val);
            }
        }

        return expressionList.get(0);
    }

    private String makeMath(final String v1, final String v2, final String operation) {
        final Integer intV1 = Integer.valueOf(v1);
        final Integer intV2 = Integer.valueOf(v2);

        switch (operation) {
            case "-":
                return String.valueOf(intV1 - intV2);
            case "+":
                return String.valueOf(intV1 + intV2);
            case "*":
                return String.valueOf(intV1 * intV2);
            case "/":
                return String.valueOf(intV1 / intV2);
            case "^":
                return String.valueOf(((int) Math.pow(Double.valueOf(v1), Double.valueOf(v2))));
            default:
                return "err";
        }
    }

    public String changeExpression(String expression) {
        final StringBuilder expressPoxFix = new StringBuilder();
        List<String> operations = new ArrayList<>();

        for (char c: expression.toCharArray()) {
            String val = String.valueOf(c);
            if (OPERATIONS.contains(val)) {
                if (operations.size() > 0 && getWeight(val) < getWeight(operations.get(operations.size() - 1))) {
                    Collections.reverse(operations);
                    operations.stream().forEach(expressPoxFix::append);
                    operations = new ArrayList<>();
                    operations.add(val);
                } else {
                    operations.add(val);
                }
            } else {
                expressPoxFix.append(val);
            }
        }
        if (operations.size() > 0) {
            Collections.reverse(operations);
            operations.stream().forEach(expressPoxFix::append);
        }
        return expressPoxFix.toString().replace("(", "").replace(")", "");
    }

    private Integer getWeight(final String operation) {
        switch (operation) {
            case "+":
            case "-":
                return 0;
            case "*":
            case "/":
                return 1;
            default:
                return 2;
        }
    }
}
