package week8.calculator.syntax;

public class Designator implements Expression {
    private String identifier;

    public Designator(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
