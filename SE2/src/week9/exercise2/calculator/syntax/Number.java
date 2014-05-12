package week9.exercise2.calculator.syntax;

import week9.exercise2.calculator.EvaluationException;
import week9.exercise2.calculator.Visitor;

public class Number implements Expression {
    private final int value;

    public Number(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void accept(Visitor v) throws EvaluationException {
        v.visit(this);
    }
}