package elevator;
import java.util.Random;

public abstract class Worker extends Person {
	
	//each person has an ID. can be used to differentiate between them but 
	//probably won't be used
	protected int personId;
		//where the worker wants to go changed by method on run-time call
		protected int currentDestination;
		//location of the person
		protected int currentFloor;
		//determines starting place, destination, and 
		protected final Random RAND = new Random();
		//stops someone changing their mind if they're waiting
		protected boolean waiting;

	//constructs person with ID and destination
	public Worker(int personId) {
		super(personId);
		waiting = false;
	}
	
	public void call() {
		super.call();
		waiting = true;
	}
}
