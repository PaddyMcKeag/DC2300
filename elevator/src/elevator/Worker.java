package elevator;

public abstract class Worker extends Person {

	//stops someone changing their mind if they're waiting
	protected boolean waiting;

	//constructs person with ID and destination
	public Worker(int personId, long seed) {
		super(personId, seed);
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
}
