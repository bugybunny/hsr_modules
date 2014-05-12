package week9.exercise2.calculator;

import week9.exercise2.calculator.syntax.BinaryExpression;
import week9.exercise2.calculator.syntax.Designator;
import week9.exercise2.calculator.syntax.Number;
import week9.exercise2.calculator.syntax.UnaryExpression;

public interface Visitor {
    void visit(Designator val) throws EvaluationException;

    void visit(BinaryExpression expr) throws EvaluationException;

    void visit(Number number) throws EvaluationException;

    void visit(UnaryExpression unaryExpr) throws EvaluationException;
}
