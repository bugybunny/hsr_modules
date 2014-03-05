package week3.exercise2;

public class MonitorSyncedRaceControl extends AbstractRaceControl {
    private int     startingGridReady    = NOF_RACE_CARS;
    private boolean raceStarted          = false;
    private boolean raceOver             = false;
    private int     waitingForLapOfHonor = NOF_RACE_CARS;

    @Override
    protected synchronized void waitForAllToBeReady()
            throws InterruptedException {
        while (startingGridReady > 0) {
            wait();
        }
    }

    @Override
    public synchronized void readyToStart() {
        startingGridReady--;
        if (startingGridReady == 0) {
            notifyAll();
        }
    }

    @Override
    public synchronized void waitForStartSignal() throws InterruptedException {
        while (!raceStarted) {
            wait();
        }
    }

    @Override
    protected synchronized void giveStartSignal() {
        raceStarted = true;
        notifyAll();
    }

    @Override
    protected synchronized void waitForFinishing() throws InterruptedException {
        while (!raceOver) {
            wait();
        }
    }

    @Override
    public synchronized boolean isOver() {
        return raceOver;
    }

    @Override
    public synchronized void passFinishLine() {
        raceOver = true;
        waitingForLapOfHonor--;
        notifyAll();
    }

    @Override
    public synchronized void waitForLapOfHonor() throws InterruptedException {
        while (waitingForLapOfHonor > 0) {
            wait();
        }
    }
}
