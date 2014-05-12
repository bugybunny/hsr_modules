package week9.exercise2.calculator;

import week9.exercise2.calculator.syntax.BinaryExpression;
import week9.exercise2.calculator.syntax.Designator;
import week9.exercise2.calculator.syntax.Number;
import week9.exercise2.calculator.syntax.Operator;
import week9.exercise2.calculator.syntax.UnaryExpression;

public class VisitorDump implements Visitor {

    @Override
    public void visit(Designator val) {
        System.out.print("DESIG [" + val.getIdentifier() + "]");
    }

    @Override
    public void visit(BinaryExpression expr) throws EvaluationException {

        System.out.print("BINEXP [");
        expr.getLeft().accept(this);
        System.out.print(", ");

        System.out.print(expr.getOperator().toString());

        System.out.print(", ");
        expr.getRight().accept(this);
        System.out.print("]");
    }

    @Override
    public void visit(Number number) {
        System.out.print(number.getValue());
    }

    @Override
    public void visit(UnaryExpression unaryExpr) throws EvaluationException {

        if (unaryExpr.getOperator() == Operator.ADD) {
            System.out.print("UNARY_PLUS [");
            unaryExpr.getSubExpression().accept(this);
            System.out.print("]");
        } else if (unaryExpr.getOperator() == Operator.SUB) {
            System.out.print("UNARY_MINUS [");
            unaryExpr.getSubExpression().accept(this);
            System.out.print("]");
        } else {
            throw new EvaluationException("Invalid unary operator");
        }
    }

}
