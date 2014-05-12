package week9.exercise1.calculator.syntax;

import week9.exercise1.calculator.EvaluationException;
import week9.exercise1.calculator.VariableContext;

public class UnaryExpression implements Expression {
    private Operator   operator;
    private Expression subExpression;

    public UnaryExpression(Operator operator, Expression subExpression) {
        this.operator = operator;
        this.subExpression = subExpression;
    }

    public Operator getOperator() {
        return operator;
    }

    public Expression getSubExpression() {
        return subExpression;
    }

    @Override
    public int evaluate(VariableContext context) throws EvaluationException {
        int value = getSubExpression().evaluate(context);
        if (operator == Operator.ADD) {
            return value;
        } else if (operator == Operator.SUB) {
            return -value;
        } else {
            throw new EvaluationException("Invalid operator");
        }
    }
}
