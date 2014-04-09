package week8.calculator.syntax;

public class UnaryExpression implements Expression {
    private Operator   operator;
    private Expression subExpression;

    public UnaryExpression(Operator operator, Expression subExpression) {
        this.operator = operator;
        this.subExpression = subExpression;
    }

    public Operator getOperator() {
        return operator;
    }

    public Expression getSubExpression() {
        return subExpression;
    }
}
