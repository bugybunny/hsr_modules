package week9.exercise1.calculator.syntax;

import week9.exercise1.calculator.EvaluationException;
import week9.exercise1.calculator.VariableContext;

public class BinaryExpression implements Expression {
    private Expression left;
    private Operator   operator;
    private Expression right;

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
    public int evaluate(VariableContext context) throws EvaluationException {
        if (operator == Operator.ADD) {
            return left.evaluate(context) + right.evaluate(context);
        } else if (operator == Operator.SUB) {
            return left.evaluate(context) - right.evaluate(context);
        } else if (operator == Operator.MUL) {
            return left.evaluate(context) * right.evaluate(context);
        } else if (operator == Operator.DIV) {
            if (right.evaluate(context) == 0) {
                throw new EvaluationException("DIV BY 0");
            }
            return left.evaluate(context) / right.evaluate(context);
        } else if (operator == Operator.MOD) {
            if (right.evaluate(context) == 0) {
                throw new EvaluationException("MOD BY 0");
            }
            return left.evaluate(context) % right.evaluate(context);
        } else {
            throw new EvaluationException("Invalid operator");
        }
    }
}
