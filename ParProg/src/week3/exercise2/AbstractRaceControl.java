package week3.exercise2;

public abstract class AbstractRaceControl {
	public final int NOF_RACE_CARS = 10;
	public final int NOF_LAPS = 5;
	private final RaceCar theCars[];
	
	public AbstractRaceControl() {
		theCars = new RaceCar[NOF_RACE_CARS];
		for (int i = 0; i < NOF_RACE_CARS; ++i) {
			theCars[i] = new RaceCar(this, "Car # " + i, NOF_LAPS);
		}
	}

	// Called by race control
	protected abstract void waitForAllToBeReady() throws InterruptedException;

	// Called by race cars
	public abstract void readyToStart();

	// Called by race control
	protected abstract void giveStartSignal();

	// Called by race cars
	public abstract void waitForStartSignal() throws InterruptedException;

	// Called by race control
	protected abstract void waitForFinishing() throws InterruptedException;

	// Called by race cars
	public abstract boolean isOver();

	public abstract void passFinishLine();

	// Called by race control and race cars
	public abstract void waitForLapOfHonor() throws InterruptedException;

	public void runRace() throws InterruptedException {
		System.out.println("Preparing race...");
		startAllEngines();
		System.out.println("Race starts soon...");
		waitForAllToBeReady();
		System.out.println("Race started: go!");
		giveStartSignal();
		waitForFinishing();
		waitForLapOfHonor();
		finishRace();
	}

	private final void startAllEngines() {
		for (int i = 0; i < NOF_RACE_CARS; ++i) {
			theCars[i].start();
		}
	}

	private final void finishRace() throws InterruptedException {
		for (int i = 0; i < NOF_RACE_CARS; ++i) {
			theCars[i].join();
		}
		System.out.println("All race cars are parked");
	}
}
