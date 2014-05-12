package week9.exercise1.calculator;

import java.util.ArrayList;

public class StatAccessSequence implements StatisticBuilder {

    private ArrayList<String> accessSequence = new ArrayList<>();

    @Override
    public void addAssignStat(String id, int value) {
        accessSequence.add("Assign: " + id + " = " + value);
    }

    @Override
    public void addGetStat(String id) {
        accessSequence.add("Get: " + id);
    }

    @Override
    public String toString() {

        String sequenceStr = "Access Sequence Statistics: \n";

        for (String stat : accessSequence)
            sequenceStr += stat + "\n";

        return sequenceStr;
    }

}
