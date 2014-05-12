package week9.exercise2.calculator;

import java.util.HashMap;
import java.util.Map;

public class StatAccessCount implements StatisticBuilder {

    private Map<String, Integer> accessCount = new HashMap<String, Integer>();

    @Override
    public void addAssignStat(String id, int value) {

        if (accessCount.get(id) == null) {
            accessCount.put(id, Integer.valueOf(0));
        }

        accessCount
                .put(id, Integer.valueOf(accessCount.get(id).intValue() + 1));
    }

    @Override
    public void addGetStat(String id) {
        if (accessCount.get(id) == null) {
            accessCount.put(id, Integer.valueOf(0));
        }

        accessCount
                .put(id, Integer.valueOf(accessCount.get(id).intValue() + 1));
    }

    @Override
    public String toString() {
        String sequenceStr = "Access Count Statistics: \n";

        for (HashMap.Entry<String, Integer> stat : accessCount.entrySet()) {
            sequenceStr += stat.getKey() + " : " + stat.getValue() + "\n";
        }

        return sequenceStr;
    }

}
