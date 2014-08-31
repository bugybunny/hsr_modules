package week3.exercise2;

import java.util.concurrent.CountDownLatch;

public class LatchRaceControl extends AbstractRaceControl {
	/** Warte auf NOF_RACE_CARS. */
	CountDownLatch carsReady = new CountDownLatch(NOF_RACE_CARS);
	/** Einer gibt das Signal. */
	CountDownLatch startSignal = new CountDownLatch(1);
	/**
	 * Sobald einer das Rennen beendet hat, fahren alle anderen ihre Runde noch
	 * fertig und warten dann gemeinsam um die Ehrenrunde zu beginnen.
	 */
	CountDownLatch isOver = new CountDownLatch(1);
	CountDownLatch readyForLapOfHonor = new CountDownLatch(NOF_RACE_CARS);

	public LatchRaceControl() {

	}

	@Override
	protected void waitForAllToBeReady() throws InterruptedException {
		carsReady.await();
	}

	@Override
	public void readyToStart() {
		carsReady.countDown();
	}

	@Override
	protected void giveStartSignal() {
		startSignal.countDown();
	}

	@Override
	public void waitForStartSignal() throws InterruptedException {
		startSignal.await();
	}

	@Override
	protected void waitForFinishing() throws InterruptedException {
		isOver.await();
	}

	@Override
	public boolean isOver() {
		return isOver.getCount() == 0;
	}

	@Override
	public void passFinishLine() {
		isOver.countDown();
		readyForLapOfHonor.countDown();
	}

	@Override
	public void waitForLapOfHonor() throws InterruptedException {
		readyForLapOfHonor.await();
	}
}
