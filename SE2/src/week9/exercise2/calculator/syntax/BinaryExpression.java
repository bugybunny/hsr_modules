package week9.exercise2.calculator.syntax;

import week9.exercise2.calculator.EvaluationException;
import week9.exercise2.calculator.Visitor;

public class BinaryExpression implements Expression {
    private final Expression left;
    private final Operator   operator;
    private final Expression right;

    public BinaryExpression(Expression left, Operator operator, Expression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Operator getOperator() {
        return operator;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public void accept(Visitor v) throws EvaluationException {
        v.visit(this);
    }
}
