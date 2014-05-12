package week9.exercise2.calculator.syntax;

import week9.exercise2.calculator.EvaluationException;
import week9.exercise2.calculator.Visitor;

public class Designator implements Expression {
    private final String identifier;

    public Designator(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void accept(Visitor v) throws EvaluationException {
        v.visit(this);
    }
}
