package week9.exercise2.calculator.lexer;

public class IntegerSymbol implements LexicalSymbol {
    private int value;

    public IntegerSymbol(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
