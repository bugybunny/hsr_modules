package week8.calculator;

public interface VariableContextBuilder {

    public abstract void assign(String identifier, int value);

    public abstract int get(String identifier) throws Exception;

}