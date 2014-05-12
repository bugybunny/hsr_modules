package week9.exercise2.calculator;

import week9.exercise2.calculator.syntax.Assignment;
import week9.exercise2.calculator.syntax.SyntaxNode;

public class MathSolver {
    public String evaluate(SyntaxNode node, VariableContext context)
            throws EvaluationException {
        if (!(node instanceof Assignment)) {
            throw new EvaluationException("Unsupported statement");
        }
        Assignment assignment = (Assignment) node;
        VisitorEvaluation evaluationVisitor = new VisitorEvaluation(context);
        assignment.getExpression().accept(evaluationVisitor);

        int value = evaluationVisitor.getLastResult();
        String variableId = assignment.getDesignator().getIdentifier();
        context.assign(variableId, value);
        return variableId + " = " + value;
    }

    public void dump(SyntaxNode node) throws EvaluationException {
        if (!(node instanceof Assignment)) {
            throw new EvaluationException("Unsupported statement");
        }

        Assignment assignment = (Assignment) node;
        VisitorDump dumpVisitor = new VisitorDump();

        System.out.print("ASSIGN [");
        assignment.getDesignator().accept(dumpVisitor);

        System.out.print(", ");

        assignment.getExpression().accept(dumpVisitor);

        System.out.println("]");
    }
}
