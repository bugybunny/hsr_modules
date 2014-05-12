package week9.exercise1.calculator.syntax;

import week9.exercise1.calculator.EvaluationException;
import week9.exercise1.calculator.VariableContext;

public interface Expression extends SyntaxNode {
    int evaluate(VariableContext context) throws EvaluationException;
}
