package week7.exercise1;

import java.util.Arrays;
import java.util.Observable;

public class TheSupercomputer extends Observable {

    private enum StatusType {
        BORED, STARTING, CONFUSED, ERROR, RECOVERING_FROM_ERROR, OVERHEATED, CALCULATING
    }

    private static final int ITERATIONS = 5;
    StatusType               status     = StatusType.BORED;
    private int              dotsCount  = 0;

    public String calculateUltimateAnswerToTheUltimateQuestionOfLifeTheUniverseAndEverything() {
        try {
            int firstPart = calculateFirstPart();
            int secondPart = calculateSecondPart();
            int modulo = calculateModulo();
            setStatus(StatusType.BORED);
            return Integer.toString(firstPart * secondPart % modulo);

        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void releaseTheKraken() {
        setChanged();
        notifyObservers();
    }

    private void setStatus(StatusType aStatus) {
        status = aStatus;
        releaseTheKraken();
    }

    private int calculateModulo() throws InterruptedException {
        setStatus(StatusType.OVERHEATED);
        doSleepLoop();
        setStatus(StatusType.CALCULATING);
        releaseTheKraken();
        doSleepLoop();
        return 53;
    }

    private int calculateSecondPart() throws InterruptedException {
        setStatus(StatusType.CONFUSED);
        doSleepLoop();
        doSleepLoop();
        setStatus(StatusType.ERROR);
        doSleepLoop();
        doSleepLoop();
        setStatus(StatusType.RECOVERING_FROM_ERROR);
        doSleepLoop();
        setStatus(StatusType.CALCULATING);
        doSleepLoop();
        return 41;
    }

    private int calculateFirstPart() throws InterruptedException {
        setStatus(StatusType.STARTING);
        doSleepLoop();
        setStatus(StatusType.CALCULATING);
        doSleepLoop();
        return 23;
    }

    private void doSleepLoop() throws InterruptedException {
        for (int i = 0; i < ITERATIONS; i++) {
            Thread.sleep(200);
            dotsCount %= ITERATIONS;
            dotsCount++;
            setChanged();
            notifyObservers();
        }
    }

    public String getStatus() {
        return status.name() + makeDotStr();
    }

    private String makeDotStr() {
        char[] chars = new char[dotsCount];
        Arrays.fill(chars, '.');
        return new String(chars);
    }
}
