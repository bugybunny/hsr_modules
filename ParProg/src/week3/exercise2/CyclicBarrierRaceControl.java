package week3.exercise2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierRaceControl extends AbstractRaceControl {
    private boolean       over;
    private CyclicBarrier allBarrier    = new CyclicBarrier(NOF_RACE_CARS + 1);
    private CyclicBarrier singleBarrier = new CyclicBarrier(1);

    public CyclicBarrierRaceControl() {
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void waitForAllToBeReady() {
        try {
            allBarrier.await();
        }
        catch (InterruptedException | BrokenBarrierException anEx) {
            anEx.printStackTrace();
        }
    }

    @Override
    public void readyToStart() {
        try {
            allBarrier.await();
        }
        catch (InterruptedException | BrokenBarrierException anEx) {
            anEx.printStackTrace();
        }
    }

    @Override
    protected void giveStartSignal() {
        allBarrier.reset();
    }

    @Override
    public void waitForStartSignal() throws InterruptedException {
        try {
            singleBarrier.await();
        }
        catch (BrokenBarrierException anEx) {
            anEx.printStackTrace();
        }
        singleBarrier.reset();
    }

    @Override
    protected void waitForFinishing() throws InterruptedException {
        try {
            singleBarrier.await();
        }
        catch (BrokenBarrierException anEx) {
            anEx.printStackTrace();
        }
        over = true;
    }

    @Override
    public boolean isOver() {
        return over;
    }

    @Override
    public void passFinishLine() {
        // nothing to do with cyclic barrier
    }

    @Override
    public void waitForLapOfHonor() throws InterruptedException {

    }
}
