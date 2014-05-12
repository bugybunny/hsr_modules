package week9.exercise2.calculator;

import week9.exercise2.calculator.syntax.BinaryExpression;
import week9.exercise2.calculator.syntax.Designator;
import week9.exercise2.calculator.syntax.Number;
import week9.exercise2.calculator.syntax.Operator;
import week9.exercise2.calculator.syntax.UnaryExpression;

public class VisitorEvaluation implements Visitor {

    private int                   lastResult;
    private final VariableContext context;

    public VisitorEvaluation(VariableContext context) {
        this.context = context;
    }

    public int getLastResult() {
        return lastResult;
    }

    @Override
    public void visit(Designator val) throws EvaluationException {
        try {
            lastResult = context.get(val.getIdentifier());
        }
        catch (Exception e) {
            throw new EvaluationException(e.getMessage());
        }
    }

    @Override
    public void visit(BinaryExpression expr) throws EvaluationException {

        expr.getLeft().accept(this);
        int leftResult = lastResult;

        expr.getRight().accept(this);
        int rightResult = lastResult;

        if (expr.getOperator() == Operator.ADD) {
            lastResult = leftResult + rightResult;
        } else if (expr.getOperator() == Operator.SUB) {
            lastResult = leftResult - rightResult;
        } else if (expr.getOperator() == Operator.MUL) {
            lastResult = leftResult * rightResult;
        } else if (expr.getOperator() == Operator.DIV) {
            if (rightResult == 0) {
                throw new EvaluationException("DIV BY 0");
            }
            lastResult = leftResult / rightResult;
        } else if (expr.getOperator() == Operator.MOD) {
            if (rightResult == 0) {
                throw new EvaluationException("MOD BY 0");
            }
            lastResult = leftResult % rightResult;
        } else {
            throw new EvaluationException("Invalid operator");
        }
    }

    @Override
    public void visit(Number number) throws EvaluationException {
        lastResult = number.getValue();
    }

    @Override
    public void visit(UnaryExpression unaryExpr) throws EvaluationException {
        unaryExpr.getSubExpression().accept(this);
        if (unaryExpr.getOperator() == Operator.SUB) {
            lastResult = -lastResult;
        } else {
            throw new EvaluationException("Invalid operator");
        }
    }

}
