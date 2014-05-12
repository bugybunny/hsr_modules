package week9.exercise1.calculator.syntax;

import week9.exercise1.calculator.EvaluationException;
import week9.exercise1.calculator.VariableContext;

public class Number implements Expression {
    private int value;

    public Number(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int evaluate(VariableContext context) throws EvaluationException {
        return value;
    }
}