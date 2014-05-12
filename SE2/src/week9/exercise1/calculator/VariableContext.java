package week9.exercise1.calculator;

import java.util.HashMap;
import java.util.List;

public class VariableContext {
    private HashMap<String, Integer> valueMap = new HashMap<String, Integer>();

    private List<StatisticBuilder>   statistics;

    public VariableContext(List<StatisticBuilder> statistics) {
        this.statistics = statistics;
    }

    public void assign(String identifier, int value) {
        valueMap.put(identifier, Integer.valueOf(value));

        for (StatisticBuilder stat : statistics) {
            stat.addAssignStat(identifier, value);
        }

    }

    public int get(String identifier) throws Exception {
        Integer value = valueMap.get(identifier);
        if (value == null) {
            throw new Exception("Undefined variable " + identifier);
        }

        for (StatisticBuilder stat : statistics) {
            stat.addGetStat(identifier);
        }

        return value.intValue();
    }
}
