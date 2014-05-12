package week9.exercise1.calculator.syntax;

import week9.exercise1.calculator.EvaluationException;
import week9.exercise1.calculator.VariableContext;

public class Designator implements Expression {
    private String identifier;

    public Designator(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public int evaluate(VariableContext context) throws EvaluationException {
        try {
            return context.get(getIdentifier());
        }
        catch (Exception e) {
            throw new EvaluationException(e.getMessage());
        }
    }

}
