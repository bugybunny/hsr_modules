package week9.exercise1.calculator;

import week9.exercise1.calculator.syntax.Assignment;
import week9.exercise1.calculator.syntax.SyntaxNode;

public class MathSolver {
    public String evaluate(SyntaxNode node, VariableContext context)
            throws EvaluationException {
        if (!(node instanceof Assignment)) {
            throw new EvaluationException("Unsupported statement");
        }
        Assignment assignment = (Assignment) node;
        int value = assignment.getExpression().evaluate(context);
        String variableId = assignment.getDesignator().getIdentifier();
        context.assign(variableId, value);
        return variableId + " = " + value;
    }
}
