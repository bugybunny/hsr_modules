package week9.exercise1.calculator.lexer;

public class IdentifierSymbol implements LexicalSymbol {
    private String identifier;

    public IdentifierSymbol(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
