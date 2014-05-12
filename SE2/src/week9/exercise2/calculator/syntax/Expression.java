package week9.exercise2.calculator.syntax;

import week9.exercise2.calculator.EvaluationException;
import week9.exercise2.calculator.Visitor;

public interface Expression extends SyntaxNode {
    void accept(Visitor v) throws EvaluationException;
}
