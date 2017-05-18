package elevator;
import java.util.Random;

public abstract class Worker extends Person {

	//stops someone changing their mind if they're waiting
	protected boolean waiting;

	//constructs person with ID and destination
	public Worker(int personId, Random rand) {
		super(personId, rand);
		waiting = false;
	}
	
	//calls the lift and makes sure they don't change their mind while waiting
	public void call() {
		super.call();
		waiting = true;
	}
	
	public void setCurrentFloor(int currentFloor) {
		super.setCurrentFloor(currentFloor);
		waiting = false;
	}
	
	public boolean getWaiting() {
		return waiting;
	}
	
	//stub for override
	public void changeDestination(double probability) {
	}
}
