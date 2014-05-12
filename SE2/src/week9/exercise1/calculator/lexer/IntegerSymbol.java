package week9.exercise1.calculator.lexer;

public class IntegerSymbol implements LexicalSymbol {
    private int value;

    public IntegerSymbol(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
