package week8.calculator.syntax;

public class Number implements Expression {
    private int value;

    public Number(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}