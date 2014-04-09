package week8.calculator;

import week8.calculator.syntax.Assignment;
import week8.calculator.syntax.BinaryExpression;
import week8.calculator.syntax.Designator;
import week8.calculator.syntax.Expression;
import week8.calculator.syntax.Number;
import week8.calculator.syntax.Operator;
import week8.calculator.syntax.SyntaxNode;
import week8.calculator.syntax.UnaryExpression;

public class MathSolver {
    public String evaluate(SyntaxNode node, VariableContextBuilder context)
            throws EvaluationException {
        if (!(node instanceof Assignment)) {
            throw new EvaluationException("Unsupported statement");
        }
        Assignment assignment = (Assignment) node;
        int value = evaluateExpression(assignment.getExpression(), context);
        String variableId = assignment.getDesignator().getIdentifier();
        context.assign(variableId, value);
        return variableId + " = " + value;
    }

    private int evaluateExpression(Expression expression,
            VariableContextBuilder context) throws EvaluationException {
        if (expression instanceof Number) {
            return ((Number) expression).getValue();
        } else if (expression instanceof Designator) {
            return evaluateDesignator((Designator) expression, context);
        } else if (expression instanceof UnaryExpression) {
            return evaluateUnaryExpression((UnaryExpression) expression,
                    context);
        } else if (expression instanceof BinaryExpression) {
            return evaluateBinaryExpression((BinaryExpression) expression,
                    context);
        } else {
            throw new AssertionError("Undefined expression");
        }
    }

    private int evaluateDesignator(Designator designator,
            VariableContextBuilder context) throws EvaluationException {
        try {
            return context.get(designator.getIdentifier());
        }
        catch (Exception e) {
            throw new EvaluationException(e.getMessage());
        }
    }

    private int evaluateUnaryExpression(UnaryExpression expression,
            VariableContextBuilder context) throws EvaluationException {
        Operator operator = expression.getOperator();
        int value = evaluateExpression(expression.getSubExpression(), context);
        if (operator == Operator.ADD) {
            return value;
        } else if (operator == Operator.SUB) {
            return -value;
        } else {
            throw new EvaluationException("Invalid operator");
        }
    }

    private int evaluateBinaryExpression(BinaryExpression expression,
            VariableContextBuilder context) throws EvaluationException {
        Operator operator = expression.getOperator();
        int left = evaluateExpression(expression.getLeft(), context);
        int right = evaluateExpression(expression.getRight(), context);
        if (operator == Operator.ADD) {
            return left + right;
        } else if (operator == Operator.SUB) {
            return left - right;
        } else if (operator == Operator.MUL) {
            return left * right;
        } else if (operator == Operator.DIV) {
            if (right == 0) {
                throw new EvaluationException("DIV BY 0");
            }
            return left / right;
        } else if (operator == Operator.MOD) {
            if (right == 0) {
                throw new EvaluationException("MOD BY 0");
            }
            return left % right;
        } else {
            throw new EvaluationException("Invalid operator");
        }
    }

    public class EvaluationException extends Exception {
        private static final long serialVersionUID = -4954487031648066245L;

        public EvaluationException(String message) {
            super(message);
        }
    }
}
