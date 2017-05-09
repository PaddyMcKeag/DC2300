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

	//constructs person with ID and destination
	public Worker(int personId) {
		super(personId);
	}
	
	public void leave() {
		currentDestination = 0;
		call();
	}
}
