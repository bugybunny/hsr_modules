package week9.exercise2.calculator.syntax;

import week9.exercise2.calculator.EvaluationException;
import week9.exercise2.calculator.Visitor;

public class UnaryExpression implements Expression {
    private final Operator   operator;
    private final Expression subExpression;

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
    public void accept(Visitor v) throws EvaluationException {
        v.visit(this);
    }
}
