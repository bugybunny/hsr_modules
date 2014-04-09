package week8.calculator;

import java.util.HashMap;

public class VariableContext implements VariableContextBuilder {
	private HashMap<String, Integer> valueMap = new HashMap<String, Integer>();

	@Override
    public void assign(String identifier, int value) {
		valueMap.put(identifier, value);
	}
	
	@Override
    public int get(String identifier) throws Exception {
		Integer value = valueMap.get(identifier);
		if (value == null) {
			throw new Exception("Undefined variable " + identifier);
		}
		return value;
	}
}
