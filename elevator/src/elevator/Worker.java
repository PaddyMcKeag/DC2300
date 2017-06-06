package elevator;
import java.util.Random;

public abstract class Worker extends Person {

	//stops someone changing their mind if they're waiting
	protected boolean waiting;

	//constructs person with ID and destination
	public Worker(int personId, Random rand) {
		super(personId, rand);
		this.waiting = false;
	}
	
	//calls the lift and makes sure they don't change their mind while waiting
	public void call() {
		super.call();
		this.waiting = true;
	}
	
	public void setCurrentFloor(int currentFloor) {
		super.setCurrentFloor(currentFloor);
		this.waiting = false;
	}
	
	public boolean getWaiting() {
		return this.waiting;
	}
	
	public void changeDestination(double probability) {
		if (!this.waiting) {
			if (rand.nextDouble() < probability) {
				this.currentDestination = this.determineDestination();
				if (this.currentDestination != this.currentFloor) {
					this.call();
				}
			}
			
		}
	}
	
	//stub for override
	public int determineDestination() {
		return -1;
	}
}
